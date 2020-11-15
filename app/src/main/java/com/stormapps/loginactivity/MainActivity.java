package com.stormapps.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Properties
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private String Username = "Stephen";
    private String Password = "12345678";

    boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind to xml layouts
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);

        //Set OnClickListener [Compares values, isCorrect = Login else reduce number of logins]

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //What next after clicking login?
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                //Check if user has inputted anything
                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter all details correctly", Toast.LENGTH_SHORT).show();
                }else{
                    //Validate credentials
                    isValid = validate(inputName, inputPassword);

                    if(!isValid){
                        counter--;
                        Toast.makeText(MainActivity.this, "Incorrect credentials entered", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("Number of Attempts Remaining: " + counter);

                        if(counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{
                        //If credentials are Correct
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        //Change to new activity
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }

                }

            }
        });

    }

    //Function to Validate if credentials match
    private boolean validate(String name, String password){
        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }
}