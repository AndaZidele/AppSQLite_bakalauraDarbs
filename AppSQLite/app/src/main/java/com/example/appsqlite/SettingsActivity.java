package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {



    TextView name, emailT, phoneT, pass, addressT, toH;
    Button btnN, btnE, btnPh, btnPass, btnA;
    MaterialButton deleteAccount;

    DBHelper myDB;
    ArrayList<String> user_id, user_name, email, phone, address, products_names, prod_ids, products_price, datums;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toH = (TextView) findViewById(R.id.toHomeFromSet);
        toH.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        name = (TextView) findViewById(R.id.settingsName);
        emailT = (TextView) findViewById(R.id.settingsEmail);
        phoneT = (TextView) findViewById(R.id.settingsPhone);
        pass = (TextView) findViewById(R.id.settingsPass);
        addressT = (TextView) findViewById(R.id.settingsAddress);

//        String epa = PrefConfig.loadEpasts(this);

        myDB = new DBHelper(this);

        getUsersData();

        deleteAccount = (MaterialButton) findViewById(R.id.btnDeleteAccount);
        deleteAccount.setOnClickListener(view -> {
            getUserId();
        });

    }


    private void deleteUser(String user) {
//        myDB = new DBHelper(this);
        sqLiteDatabase = myDB.getWritableDatabase();
        Boolean getRes = myDB.delUser(user);
        Boolean getResO = myDB.delUserO(user);
        Boolean getResC = myDB.delUserC(user);

        if (getResC == true && getRes == true && getResO ==true) {
            PrefConfig.saveUserEmail(getApplicationContext(), "Not Logged In");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void getUserId() {
        String epa = PrefConfig.loadEpasts(this);
        Cursor cursor = myDB.getDataUserId();


        if (cursor.getCount() == 0) {
            //  Toast.makeText(SettingsActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                //  Toast.makeText(SettingsActivity.this, cursor.getString(1) + " " + epa, Toast.LENGTH_SHORT).show();

                if (cursor.getString(1).equals(epa)) {
                    deleteUser(cursor.getString(0));

//                    name.setText("Full Name: " + person.getName());
//                    email.setText("Email: " +person.getEmail());
//                    phone.setText("Phone: " +person.getPhone());
//                    pass.setText("Password: " +person.getPassword());
//                    address.setText("Address: " +person.getAddress());

                }
            }
        }
    }

    private void getUsersData() {
        String epa = PrefConfig.loadEpasts(this);
        Cursor cursor = myDB.getDataUserId();


        if (cursor.getCount() == 0) {
          //  Toast.makeText(SettingsActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
              //  Toast.makeText(SettingsActivity.this, cursor.getString(1) + " " + epa, Toast.LENGTH_SHORT).show();

                if (cursor.getString(1).equals(epa)) {
                    name.setText("Full Name: " + cursor.getString(2));
                    emailT.setText("Email: " +cursor.getString(1));
                    phoneT.setText("Phone: " +cursor.getString(4));
                    pass.setText("Password: " +cursor.getString(3));
                    addressT.setText("Address: " +cursor.getString(5));

                }
            }
        }

/*
        compositeDisposable.add(myAPI.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                               @Override
                               public void accept(List<User> people) throws Exception {
                                   String vajadzigaisEpasts = epa;//"lll@l.com";//user_email;////so te dabut no ielogosanas
                                   Iterator<User> itr = people.iterator();
                                   while(itr.hasNext()){

//                                       cenaVienam = 0;
                                       User person = itr.next();

                                       String listesProduktaKategorija = person.getEmail();
                                       if ((listesProduktaKategorija.equals(vajadzigaisEpasts)) != true) {
                                           itr.remove();
                                       } else {

//                                           int userID = person.getId();
//                                           float priceFl = Float.parseFloat(strPrice);
//                                           addToCart(prod_id, userID, amount, name, price);
                                           Toast.makeText(SettingsActivity.this, "Te Esam", Toast.LENGTH_SHORT).show();
//
                                           name.setText("Full Name: " + person.getName());
                                           email.setText("Email: " +person.getEmail());
                                           phone.setText("Phone: " +person.getPhone());
                                           pass.setText("Password: " +person.getPassword());
                                           address.setText("Address: " +person.getAddress());
                                       }
                                   }

                               }


                           }
                ));
                */

    }
}