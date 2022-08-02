package com.example.tooth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

public class VerifyPhoneNo extends AppCompatActivity {

    Button verify_btn;
    TextInputLayout verificationCodeEnteredByUser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);

        verify_btn = findViewById(R.id.verify_btn);
        verificationCodeEnteredByUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);

//        //Get phone number from sign up
//        String phoneNo = getIntent().getStringExtra("phoneNo");
//
//        //Method to send verification code
//        sendVerificationCodeToUser(phoneNo);
    }

//    private void sendVerificationCodeToUser(String phoneNo) {
//        //Check firebase documentation for phone number verification instead of the following
//
//        //Paste method from firebase documentation:
//        //Authentication >> Android >> Phone Number
//        //PhoneAuthProvider
//
//        //In android studio:
//        //Tools >> Firebase >> Authentication >> Email and password authentication
//        //Add dependencies to gradle file at module app level
//
//        //Import everything necessary for the pasted method
//
//        //In firebase console:
//        //Authentication >> Enable Phone in sign in method
//
//        //Add country code to phoneNumber variable in the pasted method
//    }
}