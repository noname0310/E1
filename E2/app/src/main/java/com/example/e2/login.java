package com.example.e2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.e2.databinding.ActivityLoginBinding;

public class login extends AppCompatActivity {

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
        Toast.makeText(this, String.format("{0} {1}", email, pw), Toast.LENGTH_SHORT).show();
    }
}