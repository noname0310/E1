package com.example.e2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.e2.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private ActivityLoginBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        Binding.setEmail("");
        Binding.setPw("");

        Binding.btnLoginSignin.setOnClickListener(view -> login(Binding.getEmail(), Binding.getPw()));
        Binding.btnLoginSignup.setOnClickListener(view -> handeledFunc());
    }

    private void handeledFunc() {
        startActivity(new Intent(login.this, register.class));
    }

    private void login(String email, String pw) {

        if (email.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseFirestore
                .collection("users")
                .document(email)
                .get()
                .addOnSuccessListener(doc -> {
                    firebaseAuth
                            .signInWithEmailAndPassword(email, pw)
                            .addOnSuccessListener(runnable -> {
                                UserCache.setUser(this, doc.toObject(UserModel.class));
                                startActivity(new Intent(login.this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e -> Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
                })
                .addOnFailureListener(e -> Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}