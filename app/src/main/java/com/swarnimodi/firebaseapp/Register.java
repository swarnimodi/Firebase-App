package com.swarnimodi.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText enter_name, age, phone_number;
    private String userId;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        userId = intent.getExtras().getString("userId");

        enter_name = findViewById(R.id.enter_name);
        age = findViewById(R.id.age);
        phone_number = findViewById(R.id.phone_number);
        radioGroup = findViewById(R.id.radioGroup);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }

    public void go_to_main(View view) {

        int id = radioGroup.getCheckedRadioButtonId();
        final RadioButton radioButton = findViewById(id);

        if(radioButton.getText() == null)
            return;

        final String name = enter_name.getText().toString();
        final String user_age = age.getText().toString();
        final String number = phone_number.getText().toString();

        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(radioButton.getText().toString()).child(userId);
        currentUserDb.child("Name").setValue(name);
        currentUserDb.child("Age").setValue(user_age);
        currentUserDb.child("Number").setValue(number);
    }
}