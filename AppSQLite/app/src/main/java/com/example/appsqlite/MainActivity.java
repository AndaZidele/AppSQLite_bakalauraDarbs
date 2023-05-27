package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

import io.sentry.Sentry;

public class MainActivity extends AppCompatActivity {

    MaterialButton toCart, toLog;
    TextView toMenuView;
    Button toProd;

    DBHelper myDB;
//    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Sentry.captureMessage("testing SDK setup");

        String epa = PrefConfig.loadEpasts(this);

        toMenuView = (TextView) findViewById(R.id.toMenu);
        toMenuView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });

        myDB = new DBHelper(this);
        toCart = (MaterialButton) findViewById(R.id.mainToCart);
        toCart.setOnClickListener(view -> {
            if (epa.equals("Not Logged In") == true){

                //Snackbar mySnackbar = Snackbar;//.make(view, stringId, duration);

                String nav = "To See Cart You Have To Login!";
//                        Snackbar.make(findViewById(R.id.myCoordinatorLayout), nav,
//                                        Snackbar.LENGTH_SHORT)
//                                .show();
                Intent intent = new Intent(getApplicationContext(), HaventLoginActivity.class);
//            intent.putExtra("user_email",  user_email);
                startActivity(intent);
            } else {

                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//            intent.putExtra("user_email",  user_email);
                startActivity(intent);

            }

/*
          SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
//            for (int i=101; i<151; i++) {
//                int prod_id = (100 + i);
//                String prod_n = "Children's Product No. " + String.valueOf(i);
//                boolean boole;
//                String prod_price;
//                if (i < 126) {
//                    prod_price = String.valueOf(Float.parseFloat("24.99") + i);
//                    boole = true;
//                } else {
//                    prod_price = String.valueOf(Float.parseFloat("179.49") - i);
//                    boole = false;
//                }
//                String prod_desc = "Children's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
//                String prod_img = "Children" + String.valueOf(i) + ".png";
//                ContentValues cv = new ContentValues();
//                cv.put("id", prod_id);
//                cv.put("name", prod_n);
//                cv.put("special_offer", boole);
//                cv.put("price", prod_price);
//                cv.put("description", prod_desc);
//                cv.put("image", prod_img);
//                cv.put("category", "children");
//                sqLiteDatabase.insert("product", null, cv);
//            }
//
//            for (int i=51; i<101; i++) {
//                int prod_id = (100 + i);
//                String prod_n = "Men's Product No. " + String.valueOf(i);
//                boolean boole;
//                String prod_price;
//                if (i < 76) {
//                    prod_price = String.valueOf(Float.parseFloat("24.99") + i);
//                    boole = true;
//                } else {
//                    prod_price = String.valueOf(Float.parseFloat("179.49") - i);
//                    boole = false;
//                }
//                String prod_desc = "Men's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
//                String prod_img = "Men" + String.valueOf(i) + ".png";
//                ContentValues cv = new ContentValues();
//                cv.put("id", prod_id);
//                cv.put("name", prod_n);
//                cv.put("special_offer", boole);
//                cv.put("price", prod_price);
//                cv.put("description", prod_desc);
//                cv.put("image", prod_img);
//                cv.put("category", "men");
//                sqLiteDatabase.insert("product", null, cv);
//            }
//
            for (int i=50001; i<60001; i++) {//1-51; //2001-3001 tad 3001-4001 tad 4001-5001 tad 6001-7001 tad 7001-8001//151-1101; 1101-10101
                int prod_id = (100 + i);
                String prod_n = "Women's Product No. " + String.valueOf(i);
                boolean boole;
                String prod_price;
//                if (i < 26) {
                    prod_price = String.valueOf(Float.parseFloat("24.99") + i);
                    boole = true;
//                } else {
//                    prod_price = String.valueOf(Float.parseFloat("179.49") - i);
//                    boole = false;
//                }
                String prod_desc = "Women's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
                String prod_img = "Women" + String.valueOf(i) + ".png";
                ContentValues cv = new ContentValues();
                cv.put("id", prod_id);
                cv.put("name", prod_n);
                cv.put("special_offer", boole);
                cv.put("price", prod_price);
                cv.put("description", prod_desc);
                cv.put("image", prod_img);
                cv.put("category", "women");
                sqLiteDatabase.insert("product", null, cv);
            }

            Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                startActivity(intent);
*/

        });

        toProd = (Button) findViewById(R.id.mainToProducts);
        toProd.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });

        toLog = (MaterialButton) findViewById(R.id.mainToLogin);
        toLog.setOnClickListener(view -> {
            if (epa.equals("Not Logged In") == true){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            intent.putExtra("user_email",  user_email);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                intent.putExtra("thisUsersEmail",  epastins);
                startActivity(intent);
            }

        });


    }
}

/* SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
//            Boolean getResult = myDB.insertData( epasts,vards,parole,telefons,"");
            //Boolean res = myDB.insertProdData();
            for (int i=1; i<51; i++) {


                int prod_id = (100 + i);
                String prod_n = "Women's Product No. " + String.valueOf(i);
                boolean boole;
                String prod_price;
                if (i < 76) {
                    prod_price = String.valueOf(Float.parseFloat("24.99") + i);
                    boole = true;
                } else {
                    prod_price = String.valueOf(Float.parseFloat("79.49") - i);
                    boole = false;
                }
                String prod_desc = "Women's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
                String prod_img = "Women" + String.valueOf(i) + ".png";

                ContentValues cv = new ContentValues();
                cv.put("id", prod_id);
                cv.put("name", prod_n);
                cv.put("special_offer", boole);
                cv.put("price", prod_price);
                cv.put("description", prod_desc);
                cv.put("image", prod_img);
                cv.put("category", "women");
//                sqLiteDatabase.insert("product", null, cv);
//                if (re != null) {
//                    Toast.makeText(MainActivity.this, "Product Successfully Added to Your Cart", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Couldn't add this porduct to Your cart", Toast.LENGTH_SHORT).show();
//                }
            }*/
