package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    TextView logToMain, logToReg;
    EditText userLE, userLP;
    MaterialButton btnLog;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logToMain = (TextView) findViewById(R.id.loginToMenu);
        logToMain.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });

        logToReg = (TextView) findViewById(R.id.userGoToRegisterPage);
        logToReg.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });


        userLE = (EditText) findViewById(R.id.userLoginEmail);
        userLP = (EditText) findViewById(R.id.userLoginPassword);
        btnLog = (MaterialButton) findViewById(R.id.btnLogin);

        myDB = new DBHelper(this);

        btnLog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = userLE.getText().toString();
                String parole = userLP.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(parole)){
                    Toast.makeText(LoginActivity.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Boolean res = myDB.checkpassword(email,parole);
                    if (res == true){

                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        PrefConfig.saveUserEmail(getApplicationContext(), email);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("user_email",  user_email);

                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });
    }
}