package com.example.tooth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    ImageView logo;
    TextView banner, descript;
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;

    //Firebase variable
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To remove status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //xml banner items
        logo = findViewById(R.id.logo);
        banner = findViewById(R.id.banner);
        descript = findViewById(R.id.descript);

        //xml input items
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);

        //firebase access
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
    }

    //Validation

    // ! ! !
    // Validate Registration and login form in android studio - Form validation android 2020.
    //              (2020, January 25). [Video]. YouTube.
    //              https://www.youtube.com/watch?v=o9Y7HDkopHg

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=15){
            regUsername.setError("Username is too long");
            return false;
        }else if(!val.matches(noWhiteSpace)){
            regUsername.setError("White spaces are not allowed");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    //Save data to firebase on button click
    public void registerUser(View view) {

        if(!validateName() || !validateUsername() || !validateEmail() || !validatePhoneNo() || !validatePassword()){
            return;
        }

        //GET ALL THE VALUES FROM THE TEXTFIELDS
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

//        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
//        intent.putExtra("phoneNo", phoneNo);
//        startActivity(intent);

        //object of UserHelperClass
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);

        //use username for unique id, instead of email as it has symbols e.g. @, ., etc
        //can be used to override data already stored
        reference.child(username).setValue(helperClass);

        Toast.makeText(SignUp.this, "Registation successful", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

    //Redirect to login screen
    public void toSignIn(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }

}