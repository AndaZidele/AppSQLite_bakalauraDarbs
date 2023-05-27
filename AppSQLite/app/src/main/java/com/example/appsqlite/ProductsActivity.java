package com.example.appsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class ProductsActivity extends AppCompatActivity {


    RecyclerView recycler_search;
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    DBHelper myDB;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Product> productsList;

    ArrayList<String> name, price, desc, id, image;
//    TextView userEmail;
    TextView toH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        toH= (TextView) findViewById(R.id.productsToHome);
        toH.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        recycler_search = (RecyclerView) findViewById(R.id.recycler_search);
//        layoutManager = new LinearLayoutManager(this);
////        recycler_search.setLayoutManager(layoutManager);
//        recycler_search.setHasFixedSize(true);
//        recycler_search.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));

        myDB = new DBHelper(this);


        productsList = new ArrayList<Product>();
//        adapter = new ProductsAdapter(ProductsActivity.this, productsList);


        name = new ArrayList<>();
        desc = new ArrayList<>();
        price = new ArrayList<>();
        id = new ArrayList<>();
        adapter = new ProductsAdapter(this, name, price, desc, id, image);

        recycler_search.setAdapter(adapter);
        recycler_search.setLayoutManager(new LinearLayoutManager(this));


        getAllPerson();
    }

//    private UserAPI getAPI() {
//        return RetrofitProduct.getInstance().create(UserAPI.class);
//    }

    private void getAllPerson() {
        Intent category = getIntent();
        String kategorija = category.getStringExtra("categoryName");

//        sqLiteDatabase=myDB.getReadableDatabase();


        Cursor cursor = myDB.getDataa();//sqLiteDatabase.rawQuery("select * from product", null);
//        List<Product>people;
        if(cursor.getCount()==0){
            Toast.makeText(ProductsActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(6).equals(kategorija)){
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