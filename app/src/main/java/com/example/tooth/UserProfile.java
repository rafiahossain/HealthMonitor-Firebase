package com.example.tooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    //Variables
    TextInputLayout fullname, email, phoneNo, password;
    TextView fullnameLabel, usernameLabel;

    //Global variables to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooking variables to xml file
        fullname = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullnameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        //Method to show all data
        showAllUserData();

    }

    private void showAllUserData() {

        //Get values from intent
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");

        //SetText for all values
        fullnameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullname.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);

    }

    public void logoutUser(View view) {
        //redirect to login screen
        Intent intent = new Intent(UserProfile.this, Login.class);
        startActivity(intent);
    }

    public void update(View view) {

        if (isNameChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "Data is the same and/or cannot be updated", Toast.LENGTH_LONG).show();

    }

    private boolean isPasswordChanged() {

        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isNameChanged() {

        if (!_NAME.equals(fullname.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("name").setValue(fullname.getEditText().getText().toString());
            _NAME = fullname.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }


}