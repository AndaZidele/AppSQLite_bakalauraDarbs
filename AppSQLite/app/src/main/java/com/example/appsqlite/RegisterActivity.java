package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    TextView regToHome, regToLog;
    EditText fullName, email, phone, pass, confPass;
    MaterialButton btnReg;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regToHome = (TextView) findViewById(R.id.toRegisterMenu);
        regToHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });
        regToLog = (TextView) findViewById(R.id.userGoToLoginPage);
        regToLog.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });

        fullName = (EditText) findViewById(R.id.userFullName);
        email = (EditText) findViewById(R.id.userEmail);
        phone = (EditText) findViewById(R.id.userPhone);
        pass = (EditText) findViewById(R.id.userPassword);
        confPass = (EditText) findViewById(R.id.userConfirmPassword);
        btnReg = (MaterialButton) findViewById(R.id.btnRegister);

        myDB = new DBHelper(this);

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
              //  registerUser();
                String epasts = email.getText().toString();
                String parole = pass.getText().toString();
                String confParole = confPass.getText().toString();
                String telefons = phone.getText().toString();
                String vards = fullName.getText().toString();

                if(TextUtils.isEmpty(epasts)){
                    Toast.makeText(RegisterActivity.this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(parole)){
                    Toast.makeText(RegisterActivity.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confParole)){
                    Toast.makeText(RegisterActivity.this, "Confirm Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(telefons)){
                    Toast.makeText(RegisterActivity.this, "Phone cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(vards)){
                    Toast.makeText(RegisterActivity.this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean usercheckResult = myDB.checkemail(epasts);
                if (usercheckResult == false){
                    Boolean getResult = myDB.insertData( epasts,vards,parole,telefons,"");
                    if (getResult == true){
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        PrefConfig.saveUserEmail(getApplicationContext(), epasts);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("user_email",  user_email);

                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "User already exists. Please try with a different email or login.", Toast.LENGTH_SHORT).show();

                }




            }
        });
    }
}