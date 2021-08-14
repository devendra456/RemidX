package com.skyview.remidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username;
    TextInputEditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.edit_text);
        password=findViewById(R.id.editPass);
    }

    public void gotoVerifyOtp(View view) {
        if((username.getText().toString().trim().equals("Ankit"))&&(password.getText().toString().trim().equals("Ankit@123"))){
            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
            editor.putBoolean("islogin", true);
            editor.apply();
            startActivity(new Intent(this,HomePage.class));
        }
        else
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
    }
}