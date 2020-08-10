package com.example.e2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.e2.databinding.ActivityLoginBinding;
import com.example.e2.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private ActivityRegisterBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        Binding.setName("");
        Binding.setEmail("");
        Binding.setPw("");
        Binding.setPwcheck("");

        Binding.btnLoginSignup.setOnClickListener(view -> register(Binding.getName(), Binding.getEmail(), Binding.getPw(), Binding.getPwcheck()));
    }

    private void register(String name, String email, String pw, String pwcheck) {
        if (name.isEmpty() || email.isEmpty() || pw.isEmpty() || pwcheck.isEmpty()) {
            Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        } else if (!pw.equals(pwcheck)) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseFirestore
                .collection("users")
                .document(email)
                .set(new UserModel(name, email))
                .addOnSuccessListener(runnable -> {
                    firebaseAuth
                            .createUserWithEmailAndPassword(email, pw)
                            .addOnSuccessListener(runnable1 -> {
                                Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                });
    }
}