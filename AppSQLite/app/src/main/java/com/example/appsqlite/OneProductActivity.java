package com.example.appsqlite;

import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

public class OneProductActivity extends AppCompatActivity {


    DBHelper myDB;
    SQLiteDatabase sqLiteDatabase;
    TextView description, price, name, toH;
    ImageView image;
    MaterialButton addTo;
//    String pr_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_product);

        toH= (TextView) findViewById(R.id.oneProductToHome);
        toH.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        myDB = new DBHelper(this);

        name = (TextView) findViewById(R.id.productName);
        price = (TextView) findViewById(R.id.productPrice);
        image = (ImageView) findViewById(R.id.productImage);
        description = (TextView) findViewById(R.id.productDescription);
        addTo = (MaterialButton) findViewById(R.id.btnAddToCart);

        Intent oneProduct = getIntent();
//        String user_email = oneProduct.getStringExtra("user_email");// lietotaja epasts
        String strName = oneProduct.getStringExtra("oneName");
        String strPrice = oneProduct.getStringExtra("onePrice");
//        String strImage = oneProduct.getStringExtra("oneImage");
        String strDescr = oneProduct.getStringExtra("oneDescr");
        String strId = oneProduct.getStringExtra("oneId");// 0);

        int strIdInt = Integer.parseInt(strId);

//        if (strName!=null && strId!=0 && strPrice!=null && strDescr!=null) { //&& strImage!=null
        name.setText(strName);
        price.setText(strPrice + " EUR");
//            image.setImageResource(Integer.parseInt(strImage));
        description.setText(strDescr);


        addTo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                addToCart();

//                    Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
//                intent.putExtra("user_email",  userLE.getText().toString());
//                startActivity(intent);

                String epa = PrefConfig.loadEpasts(OneProductActivity.this);
                if (epa.equals("Not Logged In") == true){
                    String nav = "To Add Product To Cart You Have To Login!";
                    Toast.makeText(OneProductActivity.this, nav, Toast.LENGTH_LONG).show();
                } else {
                    getUserId(strIdInt, strPrice);

//                    getUsersId();//prodId, 1, strName, pri, user_email);

                }
            }
        });
    }

//    private void toCart(String strPrice, int user_id, int prod_id, Boolean bo){
//        sqLiteDatabase = myDB.getWritableDatabase();
//        Cursor cursor = myDB.getCartData();
//
//
//        if (bo==true){
//
//            Boolean getRes = myDB.incCart(prod_id, user_id);
//        } else {
//            Boolean getResult = myDB.insertCartData(prod_id,user_id,name.getText().toString(), strPrice,1);
//
//
//        }
//    }

    private void addToCart(String strPrice, int user_id, int prod_id){
//            Toast.makeText(OneProductActivity.this, "Hii from Add To Cart!!!!!!", Toast.LENGTH_SHORT).show();

       // prod_id INTEGER, user_id INTEGER, prod_name Text, price Float, amount INTEGER)");

        myDB = new DBHelper(this);

        sqLiteDatabase = myDB.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("prod_id", prod_id);
//        cv.put("user_id", user_id);
//        cv.put("prod_name", name.getText().toString());
//        cv.put("price", parseFloat(price.getText().toString()));
//        cv.put("amount", 1);

        Cursor cursor = myDB.getCartData();
        Boolean getResult = myDB.insertCartData(prod_id,user_id,name.getText().toString(), strPrice,1);

//        Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
//        startActivity(intent);
/*
        if(cursor.getCount()==0){
//            Toast.makeText(CartActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Boolean res = false;
            while (cursor.moveToNext()) {
//                Toast.makeText(CartActivity.this, cursor.getString(3) + " " +cursor.getString(2) + " " + thisUserId, Toast.LENGTH_SHORT).show();

//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(2).equals(String.valueOf(user_id)) && cursor.getString(1).equals(String.valueOf(prod_id))){
//                    prod_id.add(cursor.getString(1));
//                    user_id.add(cursor.getString(2));
//                    //setText(new DecimalFormat("####.##").format(cenaKopa));
//                    price.add(cursor.getString(4));
//                    prod_name.add(cursor.getString((3)));
                    res = true;
                    toCart(strPrice, user_id, prod_id, res);
//                    amount.add(cursor.getString(5));
                   // Boolean getResult = myDB.insertCartData(prod_id,user_id,name.getText().toString(), strPrice,1);

//                    Boolean getRes = myDB.incCart(prod_id, user_id);
//                    break;

                }

////                cursor.moveToLast().
//                if (cursor.moveToNext()==false){
//                }

            }
            Toast.makeText(OneProductActivity.this, "Hii from Just To Cart!!!!!!", Toast.LENGTH_SHORT).show();





        }*/
    }


    private void getUserId(int strId, String strPrice){//String user_email){
        String epa = PrefConfig.loadEpasts(this);
        Cursor cursor = myDB.getDataUserId();


        if(cursor.getCount()==0){
            Toast.makeText(OneProductActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
//                Toast.makeText(OneProductActivity.this, cursor.getString(1) + " " + epa , Toast.LENGTH_SHORT).show();

                if (cursor.getString(1).equals(epa)){
//                    user_id.add(cursor.getString(1));
                    addToCart(strPrice, Integer.parseInt(cursor.getString(0)), strId);
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