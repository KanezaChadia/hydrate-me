package com.example.myapplication.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText editTextEmail, editTextPassword,editTextPasswordVerif, editTextFirstName, editTextLastName;
    TextView mSignIn;
    Button buttonReg;

    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextFirstName = findViewById(R.id.firstName_signup_et);
        editTextLastName = findViewById(R.id.lastName_signup_et);

        editTextEmail = findViewById(R.id.email_signup_et);
        editTextPassword = findViewById(R.id.password_signup_et);
        editTextPasswordVerif = findViewById(R.id.re_enter_password_et);

        radioGenderGroup = (RadioGroup) findViewById(R.id.radioGrp);
        // get selected radio button from radioGroup
        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioGenderButton = (RadioButton) findViewById(selectedId);


        buttonReg = findViewById(R.id.sign_up_btn);
        buttonReg.setOnClickListener(this);

        mSignIn = findViewById(R.id.signInNow);
        mSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_up_btn){
            String email, password, firstName, lastName, passwordVerif;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            firstName = String.valueOf(editTextFirstName.getText());
            lastName =String.valueOf(editTextLastName.getText());
            passwordVerif = String.valueOf(editTextPasswordVerif.getText());

            mAuth = FirebaseAuth.getInstance();

            if (TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!password.equals(passwordVerif)){
                Toast.makeText(this, "Passwords do nor match", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener(task -> {
                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                            if (isNewUser) {
                                // User does not exist, proceed with registration
                                mAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // Sign in success, update UI with the signed-in user's information
                                                    //Log.d(TAG, "signInWithEmail:success");
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    assert user != null;
                                                    String userId = user.getUid();

                                                    DatabaseReference userRef = mDatabase.getReference("users").child(userId);
                                                    Map<String, Object> userMap = new HashMap<>();
                                                    userMap.put("firstName", firstName);
                                                    userMap.put("lastName", lastName);
                                                    userMap.put("gender", radioGenderButton.getText());

                                                    userRef.setValue(userMap).addOnCompleteListener( task1 -> {
                                                        if(task1.isSuccessful()){
                                                            Toast.makeText(SignUpActivity.this, "User is registered and data is saved to Realtime Database.", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(SignUpActivity.this, "Failed to save user data to Realtime Database.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                    updateUI(userId);

                                                    Toast.makeText(SignUpActivity.this, "Account created.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    // If sign in fails, display a message to the user.
                                                    Toast.makeText(SignUpActivity.this, "Failed to register user.", Toast.LENGTH_SHORT).show();

                                                    updateUI(null);
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(SignUpActivity.this, "User Already exist, please sign in", Toast.LENGTH_SHORT).show();
                            }
                        });

            }


        }else if (v.getId() == R.id.signInNow){
            Intent intent  = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        }


    }

    private void updateUI(String userId){

    }
}