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

public class MainActivity extends AppCompatActivity {
    EditText editText_email, editText_password;
    TextView textView_Signup;
    Button button_login_operation;

    //Firebase Initialise
    FirebaseAuth firebaseAuth;
    //Progress Dialog to engage the user to understand system is working
    ProgressDialog lprogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateComponentsMain();
        //Initiated  the Firebase Authentication Object
        firebaseAuth = FirebaseAuth.getInstance();
        //Initiated  the  ProgressDialog in current activity by this keyword
        lprogressDialog = new ProgressDialog(this);

        button_login_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store the Email and Password in String from EditText
                String login_email = editText_email.getText().toString().trim();
                String login_password = editText_password.getText().toString().trim();

                //Check Email and Password in String from EditText is vaild or not
                if (TextUtils.isEmpty(login_email)) {
                    editText_email.setError("Invaild Email-ID/Input");
                    return;
                }
                if (TextUtils.isEmpty(login_password)) {
                    editText_password.setError("Invaild Password/Input");
                    return;
                }

                //ProgressDialog Object invoke the method for message with icon,title. To Show the user
                lprogressDialog.setMessage("Processing");
                lprogressDialog.setTitle(R.string.app_name);
                lprogressDialog.setIcon(R.drawable.ic_add_alert_black_24dp);
                lprogressDialog.show();
                //Firebase Object invoke the method for sign-in of user on Firebase
                firebaseAuth.signInWithEmailAndPassword(login_email,login_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Check the user loged in successfully (Pass) it goes into if loop
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            //Start Activity for new approach..
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                            //After successful login ProgressDialog Object invoke the method to dismiss
                            lprogressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            lprogressDialog.dismiss();
                        }

                    }
                });

            }
        });
        textView_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterationActivity.class));
                finish();
            }
        });

    }

    private void initiateComponentsMain() {
        editText_email = findViewById(R.id.email_login);
        editText_password = findViewById(R.id.password_login);
        textView_Signup = findViewById(R.id.signup_txt);
        button_login_operation = findViewById(R.id.login_btn);
    }
}
