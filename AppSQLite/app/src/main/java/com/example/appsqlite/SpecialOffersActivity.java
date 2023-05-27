package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Adapters.Product;
import com.example.appsqlite.Adapters.ProductsAdapter;
import com.example.appsqlite.Helpers.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SpecialOffersActivity extends AppCompatActivity {

    RecyclerView recycler_search;
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    DBHelper myDB;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Product> productsList;

    ArrayList<String> name, price, desc, id, image;
    TextView toHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offers);

        toHome = (TextView) findViewById(R.id.spOffersToHome);
        toHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        recycler_search = (RecyclerView) findViewById(R.id.recycler_searchSp);
        myDB = new DBHelper(this);


        productsList = new ArrayList<Product>();


        name = new ArrayList<>();
        desc = new ArrayList<>();
        price = new ArrayList<>();
        id = new ArrayList<>();
        adapter = new ProductsAdapter(this, name, price, desc, id, image);

        recycler_search.setAdapter(adapter);
        recycler_search.setLayoutManager(new LinearLayoutManager(this));


        getAllPerson();
    }

    private void getAllPerson() {
//        sqLiteDatabase=myDB.getReadableDatabase();


        Cursor cursor = myDB.getDataa();//sqLiteDatabase.rawQuery("select * from product", null);
//        List<Product>people;
        if(cursor.getCount()==0){
//            Toast.makeText(SpecialOffersActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(2).equals("1")){
                    name.add(cursor.getString(1));
                    desc.add(cursor.getString(4));
                    //setText(new DecimalFormat("####.##").format(cenaKopa));
                    price.add(String.valueOf(new DecimalFormat("####.##").format(Float.parseFloat(cursor.getString(3)))));
                    id.add(cursor.getString((0)));
//                    image.add(cursor.getString(5));

                }



            }

//            Intent intent = new Intent(getApplicationContext(), DoneActivity.class);
//            startActivity(intent);
        }



       /*
        String vajadzigaKategorija = kategorija;
       //        ArrayList<Product>arrayList=new ArrayList<>();
        if(cursor.moveToFirst()){
//        if(cursor.getCount()>0)
            do {

                productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//                arrayList.add(new Product(cursor.getString(1),cursor.getString(2)));
                Iterator<Product> itr = productsList.iterator();
                while(itr.hasNext()){
                    Product person = itr.next();
                    String listesProduktaKategorija = person.getCategory();
                    if ((listesProduktaKategorija.equals(vajadzigaKategorija)) != true) {
//                        itr.remove();
                    } else { }
                }
            } while (cursor.moveToNext());

//            adapter.notifyDataSetChanged();
        }*/


//        adapter = new ProductsAdapt;
//        recycler_search.setAdapter(adapter);


    }
}