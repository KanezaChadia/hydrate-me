package com.example.hydrateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mSignIn, mSignUp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

         mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

         mSignIn = findViewById(R.id.signInNow);
         mSignUp = findViewById(R.id.signUpNow);

         mSignUp.setOnClickListener(this);
         mSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.signUpNow){
            intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);

        }else if (v.getId() == R.id.signInNow) {
            intent  = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        }
        finish();

    }
}