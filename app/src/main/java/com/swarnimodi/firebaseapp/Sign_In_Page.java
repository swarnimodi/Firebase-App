package com.swarnimodi.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_In_Page extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private EditText email_in, pass_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);
        mAuth = FirebaseAuth.getInstance();

        email_in = findViewById(R.id.email_in);
        pass_in = findViewById(R.id.pass_in);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent i = new Intent(Sign_In_Page.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }

    public void go_to_sign_up(View view) {
        startActivity(new Intent(Sign_In_Page.this, Sign_Up_Page.class));
        finish();
    }

    public void enter_main(View view){
        final String Email = email_in.getText().toString();
        final String Pass = pass_in.getText().toString();

        if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Pass)) {
            Toast.makeText(Sign_In_Page.this, "Empty Field", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(Sign_In_Page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(Sign_In_Page.this, "Sign In Error", Toast.LENGTH_SHORT).show();
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