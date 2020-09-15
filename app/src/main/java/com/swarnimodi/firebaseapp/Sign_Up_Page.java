package com.swarnimodi.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_Up_Page extends AppCompatActivity{

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    EditText email_up, pass_up, verify;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        mAuth = FirebaseAuth.getInstance();

        email_up = findViewById(R.id.email_up);
        pass_up = findViewById(R.id.pass_up);
        verify = findViewById(R.id.verify);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent i = new Intent(Sign_Up_Page.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }

    public void go_to_main(View view){

        String email = email_up.getText().toString();
        String pass = pass_up.getText().toString();
        String confirm = verify.getText().toString();

        if(!(pass.equals(confirm))){
            Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)) {
            Toast.makeText(Sign_Up_Page.this, "Empty Field", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Sign_Up_Page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!(task.isSuccessful())){
                    Toast.makeText(Sign_Up_Page.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
