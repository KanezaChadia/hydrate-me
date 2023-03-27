package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.MainWindow.MainActivity;
import com.example.myapplication.UserManagement.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.Thread.sleep;

public class SplashScreen extends Activity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(new Runnable() {
            public void run() {

                try {
                    sleep(4000);

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();

                    if (user != null){

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Intent splashIntent = new Intent (getApplicationContext(), SignInActivity.class);
                        startActivity(splashIntent);
                    }


                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        myThread.start();

    }}