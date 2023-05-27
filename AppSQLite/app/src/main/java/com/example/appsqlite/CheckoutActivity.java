package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsqlite.Adapters.CartsAdapter;
import com.example.appsqlite.Helpers.DBHelper;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    TextView productsNames, totalCena, toH;
    EditText userN, userE, userPh, userA;
    MaterialButton btnMakeOrder;


    DBHelper myDB;
    ArrayList<String> user_id, prod_id, prod_name, price, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        toH = (TextView) findViewById(R.id.toHFromChe);
        toH.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        myDB = new DBHelper(this);

        prod_id = new ArrayList<>();
        user_id = new ArrayList<>();
        prod_name = new ArrayList<>();
        price = new ArrayList<>();
        amount = new ArrayList<>();


        getUserId();

        btnMakeOrder = (MaterialButton) findViewById(R.id.btnMakeOrder);
        btnMakeOrder.setOnClickListener(view -> {
            getUserIdOrderam();
            //1)Order: userId, userName, userEmail, userPhone, userAddress, productsName(String virkne), orderPrice, datums, statuss(true/false=piegadats/nepiegadats)

//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
        });
    }

    private void makeProdIds(int user_id){

        Cursor cursor = myDB.getCartData();
        if(cursor.getCount()==0){
            Toast.makeText(CheckoutActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            float cenaVienam, cenaKopa = 0;
            String produktuVirkne = "";
            while (cursor.moveToNext()) {
                cenaVienam = 0;
//                int produktaUsers =

//            productsList.add(new Product(cursor.getInt(0),cursor.getInt(1)>0,cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//0-id; 1-name; 2-spec_off; 3-price; 4-desc; 5-img; 6-cat;
                if (cursor.getString(2).equals(String.valueOf(user_id))){
//                    prod_id.add(cursor.getString(1));
//                    user_id.add(cursor.getString(2));
//                    //setText(new DecimalFormat("####.##").format(cenaKopa));
//                    price.add(cursor.getString(3));
//                    prod_name.add(cursor.getString((4)));
//
//                    amount.add(cursor.getString(5));
                    if (produktuVirkne.equals("")) {
                        if (cursor.getString(5).equals("1")) {
                            produktuVirkne = produktuVirkne + String.valueOf(cursor.getString(1));
                        } else {
                            produktuVirkne = produktuVirkne + String.valueOf(cursor.getString(1)) + " ( x " + String.valueOf(cursor.getString(5)) +")";

                        }
                    } else {
                        if (cursor.getString(5).equals("1")) {
                            produktuVirkne = produktuVirkne + "; " + String.valueOf(cursor.getString(1));
                        } else {
                            produktuVirkne = produktuVirkne + "; " + String.valueOf(cursor.getString(1)) + " ( x " + String.valueOf(cursor.getString(5)) +")";

                        }

                    }
                    cenaVienam = Integer.parseInt(cursor.getString(5)) * Float.parseFloat(cursor.getString(4));
//                    cenaVienam = Integer.parseInt(cursor.getString(5)) * person.getPrice();


                }

                cenaKopa = cenaKopa + cenaVienam;
            }
//                                   totalCena = (TextView) findViewById(R.id.total_id);
            String cenina = new DecimalFormat("####.##").format(cenaKopa);
            userA = (EditText) findViewById(R.id.changeAddress);
            String address = String.valueOf(userA.getText());

            if (address.equals("")){
                Toast.makeText(CheckoutActivity.this, "Please Write Your Address", Toast.LENGTH_SHORT).show();
            } else {
                makeOrder(user_id, produktuVirkne, cenina);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }

        }
    }

    private void makeOrder(int user_id, String prod_ids, String cena){
        userN = (EditText) findViewById(R.id.changeName);
        userE = (EditText) findViewById(R.id.changeEmail);
        userPh = (EditText) findViewById(R.id.changePhone);
        userA = (EditText) findViewById(R.id.changeAddress);
        String email = String.valueOf(userE.getText());
        String phone = String.valueOf(userPh.getText());
        String address = String.valueOf(userA.getText());
        String name = String.valueOf(userN.getText());
        productsNames = (TextView) findViewById(R.id.checkout_txt_name);
        String prod_names = String.valueOf(productsNames.getText());
//        String userId = String.valueOf(user_id);


        //insertOrderData
        myDB = new DBHelper(this);

        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();


        Boolean getResult = myDB.insertOrderData(user_id, name, email, phone, address, prod_names, prod_ids, cena);

        if (getResult == true){
            sendUserToNextView();

        }
    }

    private void sendUserToNextView() {
        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
//        intent.putExtra("thisUsersEmail",  epastins);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void getUserIdOrderam(){//String user_email){
        String epa = PrefConfig.loadEpasts(this);
        userN = (EditText) findViewById(R.id.changeName);
        userE = (EditText) findViewById(R.id.changeEmail);
        userPh = (EditText) findViewById(R.id.changePhone);
        userA = (EditText) findViewById(R.id.changeAddress);
        Cursor cursor = myDB.getDataUserId();
        if (cursor.getCount() == 0) {
            Toast.makeText(CheckoutActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                if (cursor.getString(1).equals(epa)) {
//                    userN.setText(cursor.getString((2)));
//                    userE.setText(cursor.getString((1)));
//                    userPh.setText(cursor.getString((4)));
//                    userA.setText(cursor.getString((5)));
                    makeProdIds(Integer.parseInt(cursor.getString((0))));
                   // getAllCart(Integer.parseInt(cursor.getString(0)));
                }
            }
        }

       /* compositeDisposable.add(myAPI.getUserList()
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

                                           int userID = person.getId();
//                                           String izvadit = String.valueOf(userID);
//                                           float priceFl = Float.parseFloat(strPrice);
//                                           addToCart(prod_id, userID, amount, name, price);
//                                           Toast.makeText(CartActivity.this, izvadit+" ", Toast.LENGTH_SHORT).show();
//                                           addToCart(prodId, userID, 1, strName, priceFl);


                                           makeProdIds(userID);//te vjg datus iekavas
//
                                       }
                                   }

//                                   int produkta_id = people.get(2).getId();
//                                   Toast.makeText(ProductsActivity.this, "TE: " + produkta_id, Toast.LENGTH_SHORT).show();
                               }


                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(CheckoutActivity.this, "Not found from All Products" , Toast.LENGTH_SHORT).show();
                               }
                           }
                ));*/
    }

    private void getUserId() {//String user_email){
        String epa = PrefConfig.loadEpasts(this);
        userN = (EditText) findViewById(R.id.changeName);
        userE = (EditText) findViewById(R.id.changeEmail);
        userPh = (EditText) findViewById(R.id.changePhone);
        userA = (EditText) findViewById(R.id.changeAddress);
        Cursor cursor = myDB.getDataUserId();
        if (cursor.getCount() == 0) {
            Toast.makeText(CheckoutActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                if (cursor.getString(1).equals(epa)) {
                    userN.setText(cursor.getString((2)));
                    userE.setText(cursor.getString((1)));
                    userPh.setText(cursor.getString((4)));
                    userA.setText(cursor.getString((5)));
                    getAllCart(Integer.parseInt(cursor.getString(0)));
                }
            }
        }
    }

    private void getAllCart(int thisUserId){
        productsNames = (TextView) findViewById(R.id.checkout_txt_name);
        totalCena = (TextView) findViewById(R.id.checkout_txt_price);

//        String epa = PrefConfig.loadEpasts(this);
//        Toast.makeText(CheckoutActivity.this, "We have a user!!!" , Toast.LENGTH_SHORT).show();
            Cursor cursor = myDB.getCartData();
            if(cursor.getCount()==0){
//                Toast.makeText(CheckoutActivity.this, "Not Found Any Items", Toast.LENGTH_SHORT).show();
                return;
            } else {
                double cenaVienam;
                double cenaKopa = 0;
                String produktuVirkne = "";
                while (cursor.moveToNext()) {
//                    Toast.makeText(CheckoutActivity.this, cursor.getString(3) + " " +cursor.getString(2) + " " + thisUserId, Toast.LENGTH_SHORT).show();
                    cenaVienam = 0;
                    if (cursor.getString(2).equals(String.valueOf(thisUserId))){
                        if (produktuVirkne.equals("")) {
                            if (cursor.getString(5).equals(String.valueOf(1))) {
                                produktuVirkne = produktuVirkne + cursor.getString((3));
                            } else {
                                produktuVirkne = produktuVirkne + cursor.getString((3)) + " (" + String.valueOf(cursor.getString(5)) +")";

                            }
                        } else {
                            if (cursor.getString(5).equals(String.valueOf(1))) {
                                produktuVirkne = produktuVirkne + "; " + cursor.getString((3));
                            } else {
                                produktuVirkne = produktuVirkne + "; " + cursor.getString((3)) + " (" + String.valueOf(cursor.getString(5)) +")";
                            }
                        }
                        cenaVienam = Integer.parseInt(cursor.getString(5)) * Float.parseFloat(cursor.getString(4));
                    }

                    cenaKopa = cenaKopa + cenaVienam;
                }
                String cenina = new DecimalFormat("####.##").format(cenaKopa);
                totalCena.setText("Price: " + cenina + " EUR");
                productsNames.setText(produktuVirkne);



//                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                startActivity(intent);


            }
    }
}