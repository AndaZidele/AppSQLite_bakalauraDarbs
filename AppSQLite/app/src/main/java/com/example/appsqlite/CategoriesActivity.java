package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class CategoriesActivity extends AppCompatActivity {

    MaterialButton toM, toW, toCh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        toM = (MaterialButton) findViewById(R.id.toMen);
        toM.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
//            intent.putExtra("user_email",  user_email);
            intent.putExtra("categoryName",  "men");

            startActivity(intent);
        });
        toW = (MaterialButton) findViewById(R.id.toWomen);
        toW.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
//            intent.putExtra("user_email",  user_email);
            intent.putExtra("categoryName",  "women");
            startActivity(intent);
        });

        toCh = (MaterialButton) findViewById(R.id.toChildren);
        toCh.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
//            intent.putExtra("user_email",  user_email);

            intent.putExtra("categoryName",  "children");
            startActivity(intent);
        });


    }
}