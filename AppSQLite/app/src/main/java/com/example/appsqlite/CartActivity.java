package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Adapters.CartsAdapter;
import com.example.appsqlite.Adapters.ProductsAdapter;
import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CartActivity extends AppCompatActivity {

//    CompositeDisposable compositeDisposable = new CompositeDisposable();
//    UserAPI myAPI;

    DBHelper myDB;

    RecyclerView recycler_search;
    LinearLayoutManager layoutManager;
    CartsAdapter adapter;

    TextView totalCena, toHome;

    MaterialButton btnOrder;

    ArrayList<String> user_id, prod_id, prod_name, price, amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        myDB = new DBHelper(this);

        recycler_search = (RecyclerView) findViewById(R.id.productListInCart);
//        layoutManager = new LinearLayoutManager(this);
//        recycler_search.setLayoutManager(layoutManager);
//        recycler_search.setHasFixedSize(true);
//        recycler_search.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));


        prod_id = new ArrayList<>();
        user_id = new ArrayList<>();
        prod_name = new ArrayList<>();
        price = new ArrayList<>();
        amount = new ArrayList<>();

        adapter = new CartsAdapter(this,myDB, prod_id, user_id, prod_name, price, amount);

        recycler_search.setAdapter(adapter);
        recycler_search.setLayoutManager(new LinearLayoutManager(this));

        toHome = (TextView) findViewById(R.id.toHomeFromCart);
        toHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });


//        float kopejaCena = 0;
//        String epa = PrefConfig.loadEpasts(this);
//        if (epa.equals("Not Logged In") == true){
//
//            //Snackbar mySnackbar = Snackbar;//.make(view, stringId, duration);
//
//            String nav = "To Add Product To Cart You Have To Login!";
////                        Snackbar.make(findViewById(R.id.myCoordinatorLayout), nav,
////                                        Snackbar.LENGTH_SHORT)
////                                .show();
//            Toast.makeText(OneProductActivity.this, nav, Toast.LENGTH_LONG).show();
//        } else {
        getUserId();//prodId, 1, strName, pri, user_email);

        btnOrder = (MaterialButton) findViewById(R.id.btnMakeOrder);
        btnOrder.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
//            intent.putExtra("user_email",  user_email);
            startActivity(intent);
        });
//        }

//        String email = "2";
//        String esosaLietotajaEpasts = "lll@l.com";
//       getUserId();//esosaLietotajaEpasts);


//        getAllCart(2);


    }

    private void getUserId(){//String user_email){
        String epa = PrefConfig.loadEpasts(this);
        Cursor cursor = myDB.getDataUserId();


        if(cursor.getCount()==0){
//            Toast.makeText(CartActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
//                Toast.makeText(CartActivity.this, cursor.getString(1) + " " + epa , Toast.LENGTH_SHORT).show();

                if (cursor.getString(1).equals(epa)){
//                    user_id.add(cursor.getString(1));
                    getAllCart(Integer.parseInt(cursor.getString(0)));
//                    desc.add(cursor.getString(4));
//                    //setText(new DecimalFormat("####.##").format(cenaKopa));
//                    price.add(cursor.getString(3));
//                    id.add(cursor.getString((0)));
//                    image.add(cursor.getString(5));

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
                                   String vajadzigaisEpasts = epa;
                                   Iterator<User> itr = people.iterator();
                                   while(itr.hasNext()){
                                       User person = itr.next();
                                       String listesProduktaKategorija = person.getEmail();
                                       if ((listesProduktaKategorija.equals(vajadzigaisEpasts)) != true) {
                                           itr.remove();
                                       } else {

                                           int userID = person.getId();
                                           getAllCart(userID);
                                       }
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(CartActivity.this, "Not found from All Products" , Toast.LENGTH_SHORT).show();
                               }
                           }
                ));
        */
    }

    private void getAllCart(int thisUserId) {
        String epa = PrefConfig.loadEpasts(this);
//        Toast.makeText(CartActivity.this, "We have a user!!!" , Toast.LENGTH_SHORT).show();

        Cursor cursor = myDB.getCartData();
        if(cursor.getCount()==0){
//            Toast.makeText(CartActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            float cenaVienam, cenaKopa = 0;

            while (cursor.moveToNext()) {
//                Toast.makeText(CartActivity.this, cursor.getString(3) + " " +cursor.getString(2) + " " + thisUserId, Toast.LENGTH_SHORT).show();

                cenaVienam = 0;

//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(2).equals(String.valueOf(thisUserId))){
                    prod_id.add(cursor.getString(1));
                    user_id.add(cursor.getString(2));
                    //setText(new DecimalFormat("####.##").format(cenaKopa));
                    price.add(cursor.getString(4));
                    prod_name.add(cursor.getString((3)));

                    amount.add(cursor.getString(5));
                    cenaVienam = Integer.parseInt(cursor.getString(5)) * Float.parseFloat(cursor.getString(4));
                }
                cenaKopa = cenaKopa + cenaVienam;



            }
            totalCena = (TextView) findViewById(R.id.total_id);
            totalCena.setText(new DecimalFormat("####.##").format(cenaKopa));


//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
        }
/*
        compositeDisposable.add(myAPI.getUserCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Cart>>() {
                               @Override
                               public void accept(List<Cart> people) throws Exception {
                                   float cenaVienam, cenaKopa = 0;
                                   Iterator<Cart> itr = people.iterator();
                                   while (itr.hasNext()) {
                                       cenaVienam = 0;
                                       Cart person = itr.next();
                                       int produktaUsers = person.getUsers_id();
                                       if (produktaUsers != thisUserId) {
                                           itr.remove();
                                       } else {
                                           cenaVienam = person.getAmount() * person.getPrice();
                                       }
                                       cenaKopa = cenaKopa + cenaVienam;
                                   }

                                   totalCena = (TextView) findViewById(R.id.total_id);
                                   totalCena.setText(new DecimalFormat("####.##").format(cenaKopa));
                                   adapter = new CartsAdapter(people, epa);
                                   recycler_search.setAdapter(adapter);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(CartActivity.this, "Not found from All Products" + thisUserId, Toast.LENGTH_SHORT).show();
                               }
                           }
                ));*/

    }

}