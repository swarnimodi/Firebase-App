package com.swarnimodi.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void to_sign_in(View view) {
        Intent intent = new Intent(this, Sign_In_Page.class);
        startActivity(intent);
        finish();
    }

    public void to_sign_up(View view){
        Intent i = new Intent(this, Sign_Up_Page.class);
        startActivity(i);
        finish();
    }
}