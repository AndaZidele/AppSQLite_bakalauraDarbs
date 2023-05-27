package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Adapters.OrdersAdapter;
import com.example.appsqlite.Adapters.ProductsAdapter;
import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recycler_search;
    LinearLayoutManager layoutManager;
    OrdersAdapter adapter;

    MaterialButton logOut, settings;
    TextView toHome;

    DBHelper myDB;
    ArrayList<String> user_id, user_name, email, phone, address, products_names, prod_ids, products_price, datums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toHome = (TextView) findViewById(R.id.toHomeFromProfile);
        toHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        settings = (MaterialButton) findViewById(R.id.btnSettings);
        settings.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        logOut = (MaterialButton) findViewById(R.id.btnLogout);
        logOut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            PrefConfig.saveUserEmail(getApplicationContext(), "Not Logged In");
            startActivity(intent);
        });

        recycler_search = (RecyclerView) findViewById(R.id.profile_recycle);

        myDB = new DBHelper(this);

        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();
        address = new ArrayList<>();
        prod_ids = new ArrayList<>();;
        products_names = new ArrayList<>();
        products_price = new ArrayList<>();
        datums = new ArrayList<>();

        adapter = new OrdersAdapter(this, user_id, user_name, email, phone, address, products_names, prod_ids,  products_price,  datums);

        recycler_search.setAdapter(adapter);
        recycler_search.setLayoutManager(new LinearLayoutManager(this));

        getUserId();
    }

    private void getAllOrders(int user_id_){

        String epa = PrefConfig.loadEpasts(this);
//        Toast.makeText(ProfileActivity.this, "We have a user!!!" , Toast.LENGTH_SHORT).show();

        Cursor cursor = myDB.getOrdersData();
        if(cursor.getCount()==0){
//            Toast.makeText(ProfileActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//                Toast.makeText(ProfileActivity.this, cursor.getString(3) + " " +cursor.getString(2) + " " + user_id_, Toast.LENGTH_SHORT).show();

//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(1).equals(String.valueOf(user_id_))){
//                    prod_id.add(cursor.getString(1));
//                    user_id.add(cursor.getString(2));
//                    //setText(new DecimalFormat("####.##").format(cenaKopa));
//                    price.add(cursor.getString(3));
//                    prod_name.add(cursor.getString((4)));
//
//                    amount.add(cursor.getString(5));


                    user_id.add(cursor.getString(1));
                    user_name.add(cursor.getString(2));
                    email.add(cursor.getString(3));
                    phone.add(cursor.getString(4));
                    address.add(cursor.getString(5));
                    prod_ids.add(cursor.getString(7));
                    products_names.add(cursor.getString(6));
                    products_price.add(cursor.getString(8));
                    datums.add(cursor.getString(9));

                }



            }

//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
        }

    }

    private void getUserId() {//String user_email){
        String epa = PrefConfig.loadEpasts(this);
//        String epa = PrefConfig.loadEpasts(this);
        Cursor cursor = myDB.getDataUserId();


        if(cursor.getCount()==0){
            Toast.makeText(ProfileActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
//                Toast.makeText(ProfileActivity.this, cursor.getString(1) + " " + epa, Toast.LENGTH_SHORT).show();

                if (cursor.getString(1).equals(epa)) {
//                    user_id.add(cursor.getString(1));
                    getAllOrders(Integer.parseInt(cursor.getString(0)));
//                    desc.add(cursor.getString(4));
//                    //setText(new DecimalFormat("####.##").format(cenaKopa));
//                    price.add(cursor.getString(3));
//                    id.add(cursor.getString((0)));
//                    image.add(cursor.getString(5));

                }


            }
        }
    }


}