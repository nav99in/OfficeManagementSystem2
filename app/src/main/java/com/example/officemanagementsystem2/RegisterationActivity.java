package com.example.officemanagementsystem2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {
    EditText registration_email, registration_password;
    Button button_registration;
    TextView textView_login;
    String reg_email, reg_password;

    //Create the Firebase Authentication Object
    private FirebaseAuth firebaseAuth;

    //Progress Dialog to engage the user to understand system is working
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        initiateComponents();

        //Initiated  the Firebase Authentication Object
        firebaseAuth = FirebaseAuth.getInstance();
        //Initiated  the  ProgressDialog in current activity by this keyword
        progressDialog = new ProgressDialog(this);


    }

    private void initiateComponents() {
        textView_login = findViewById(R.id.login_txt);
        registration_email = findViewById(R.id.email_registration);
        registration_password = findViewById(R.id.password_registration);
        button_registration = findViewById(R.id.register_btn);
        operationsOfRegistration();
    }

    private void operationsOfRegistration() {
        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        button_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_email = registration_email.getText().toString().trim();
                reg_password = registration_password.getText().toString().trim();

                //Validation
                if (TextUtils.isEmpty(reg_email)) {
                    registration_email.setError("Email is required!!");
                    return;
                }

                if (TextUtils.isEmpty(reg_password)) {
                    registration_email.setError("Password is required!!");
                    return;
                }

                //ProgressDialog Object invoke the method for message with icon,title. To Show the user
                progressDialog.setMessage("Processing");
                progressDialog.setTitle(R.string.app_name);
                progressDialog.setIcon(R.drawable.ic_add_alert_black_24dp);
                progressDialog.show();

                //Firebase Object invoke the method for creation of user on Firebase
                firebaseAuth.createUserWithEmailAndPassword(reg_email, reg_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Check the user created successfully (Pass) it goes into if loop
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterationActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            //Start Activity for new approach..
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            //After successful login ProgressDialog Object invoke the method to dismiss
                            progressDialog.dismiss();
                            finish();

                        } else {
                            Toast.makeText(RegisterationActivity.this, "Authentication Issue", Toast.LENGTH_SHORT).show();
                            progressDialog.setMessage("Authentication Fail");
                            progressDialog.dismiss();
                        }

                    }
                });
            }
        });
    }
}
