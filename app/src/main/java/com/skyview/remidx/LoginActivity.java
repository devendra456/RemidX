package com.skyview.remidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        if((username.getText().toString().equals("Ankit"))&&(password.getText().toString().equals("Ankit@123")))
            startActivity(new Intent(this,HomePage.class));
        else
            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
    }
}