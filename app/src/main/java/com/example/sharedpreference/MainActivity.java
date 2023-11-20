package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharedPrefManager sharedPrefManager;
    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getIsLogin()){
            startHomeUI();
        }else {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            login();
        }
    }
    private void  startHomeUI(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        finishAffinity();
        startActivity(intent);
    }
    private void login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Username dan pasword tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String spUsername = sharedPrefManager.getUsername();
                            String spPassword = sharedPrefManager.getPassword();

                            if (username.equals(spUsername) && password.equals(spPassword)){
                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                sharedPrefManager.saveIsLogin(true);
                                finishAffinity();
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this,"Username atau password salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },3000);
                }
            }
        });
    }
}