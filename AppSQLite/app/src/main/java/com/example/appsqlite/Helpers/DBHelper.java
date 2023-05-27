package com.example.appsqlite.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//cods no saites: https://www.youtube.com/watch?v=o9CVZ1gQgQo
public class DBHelper extends SQLiteOpenHelper {
//    public DBHelper(Context context){
//        super(context, "AppDB",null,1);
//    }
    public DBHelper(Context context) {
        super(context, "AppDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB){
        myDB.execSQL("create Table user(id INTEGER PRIMARY KEY AUTOINCREMENT , email Text, name Text,  password Text, phone Text, address Text)");

        myDB.execSQL("create Table cart(id INTEGER PRIMARY KEY AUTOINCREMENT ,prod_id Text, user_id Text, prod_name Text, price Text, amount Text)");

        myDB.execSQL("create Table orders(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id Text, user_name Text, email Text, phone Text, address Text, products_names Text, prod_ids Text, products_price Text, datums Text)");

        myDB.execSQL("create Table product(id INTEGER PRIMARY KEY AUTOINCREMENT , name Text, special_offer Text, price Text, description Text, image Text, category Text)");


        myDB.execSQL("create Table store(id INTEGER PRIMARY KEY AUTOINCREMENT , name Text, special_offer Text, price Text, description Text, image Text, category Text)");


//        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
        for (int i=1; i<51; i++) {//1-51; //2001-3001 tad 3001-4001 tad 4001-5001 tad 6001-7001 tad 7001-8001//151-1101; 1101-10101
            int prod_id = (100 + i);
            String prod_n = "Women's Product No. " + String.valueOf(i);
            boolean boole;
            String prod_price;
                if (i < 26) {
            prod_price = String.valueOf(Float.parseFloat("24.99") + i);
            boole = true;
                } else {
                    prod_price = String.valueOf(Float.parseFloat("99.49") - i);
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
            myDB.insert("product", null, cv);
        }
        for (int i=51; i<101; i++) {//1-51; //2001-3001 tad 3001-4001 tad 4001-5001 tad 6001-7001 tad 7001-8001//151-1101; 1101-10101
            int prod_id = (100 + i);
            String prod_n = "Men's Product No. " + String.valueOf(i);
            boolean boole;
            String prod_price;
            if (i < 76) {
                prod_price = String.valueOf(Float.parseFloat("24.99") + i);
                boole = true;
            } else {
                prod_price = String.valueOf(Float.parseFloat("199.49") - i);
                boole = false;
            }
            String prod_desc = "Men's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
            String prod_img = "Men" + String.valueOf(i) + ".png";
            ContentValues cv = new ContentValues();
            cv.put("id", prod_id);
            cv.put("name", prod_n);
            cv.put("special_offer", boole);
            cv.put("price", prod_price);
            cv.put("description", prod_desc);
            cv.put("image", prod_img);
            cv.put("category", "men");
            myDB.insert("product", null, cv);
        }
        for (int i=101; i<151; i++) {//1-51; //2001-3001 tad 3001-4001 tad 4001-5001 tad 6001-7001 tad 7001-8001//151-1101; 1101-10101
            int prod_id = (100 + i);
            String prod_n = "Children's Product No. " + String.valueOf(i);
            boolean boole;
            String prod_price;
            if (i < 126) {
                prod_price = String.valueOf(Float.parseFloat("24.99") + i);
                boole = true;
            } else {
                prod_price = String.valueOf(Float.parseFloat("199.49") - i);
                boole = false;
            }
            String prod_desc = "Children's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
            String prod_img = "Children" + String.valueOf(i) + ".png";
            ContentValues cv = new ContentValues();
            cv.put("id", prod_id);
            cv.put("name", prod_n);
            cv.put("special_offer", boole);
            cv.put("price", prod_price);
            cv.put("description", prod_desc);
            cv.put("image", prod_img);
            cv.put("category", "children");
            myDB.insert("product", null, cv);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists user");
        myDB.execSQL("drop Table if exists cart");
        myDB.execSQL("drop Table if exists orders");
        myDB.execSQL("drop Table if exists product");
        myDB.execSQL("drop Table if exists store");
    }

    public Boolean insertData(String email, String name, String password, String phone, String address){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        long result = myDB.insert("user", null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getAmount(Integer prod_id, Integer user_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
//        String pId = String.valueOf(prod_id);
//        String uId = String.valueOf(user_id);

        Cursor cursor = myDB.rawQuery("select * from cart where prod_id = ? and user_id = ?",new String[] {String.valueOf(prod_id), String.valueOf(user_id)});
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            return cursor.getString(5);
        } else {
            return "0";
        }
//        } else {
//            return false;
//        }
    }

    public  Boolean incCart(Integer prod_id, Integer user_id, Integer amon){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String am = String.valueOf(amon);
        int a = Integer.parseInt(am);
            int amo = a + 1;
            ContentValues cv = new ContentValues();
            cv.put("amount", String.valueOf(amo));
            long result = myDB.update("cart", cv, "prod_id = ? and user_id= ?", new String[]{String.valueOf(prod_id), String.valueOf(user_id)});
            return true;
    }

    public  Boolean decCart(Integer prod_id, Integer user_id, Integer amon){
        SQLiteDatabase myDB = this.getWritableDatabase();
//        String am = String.valueOf(amon);//getAmount(prod_id, user_id);
//        int a = Integer.parseInt(am);
////        if (a>1) {
//            int amo = a - 1;
//        ContentValues cv = new ContentValues();
            ContentValues cv = new ContentValues();
            cv.put("amount", String.valueOf(amon));
            long result = myDB.update("cart", cv, "prod_id = ? and user_id= ?", new String[]{String.valueOf(prod_id), String.valueOf(user_id)});
//        } else {
//            Boolean dz = delCart(prod_id, user_id);
//        }
        return true;
    }

    public  Boolean delCart(Integer prod_id, Integer user_id){
        SQLiteDatabase myDB = this.getWritableDatabase();
        long result = myDB.delete("cart", "prod_id = ? and user_id= ?", new String[]{String.valueOf(prod_id), String.valueOf(user_id)});
        return true;
    }

    public  Boolean delUser(String user_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        long result = myDB.delete("user", "id= ?", new String[]{user_id});

        return true;
    }

    public  Boolean delUserO(String user_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        long result = myDB.delete("orders", "user_id= ?", new String[]{user_id});

        return true;
    }

    public  Boolean delUserC(String user_id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        long result = myDB.delete("cart", "user_id= ?", new String[]{user_id});

        return true;
    }

    //userId, name, email, phone, address, prod_names, prod_ids, cena
    public Boolean insertOrderData(Integer userId, String name, String email, String phone, String address, String prod_names, String prod_ids, String cena){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String datums = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());

        cv.put("user_id", userId);
        cv.put("user_name", name);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("address", address);
        cv.put("products_names", prod_names);
        cv.put("prod_ids", prod_ids);
        cv.put("products_price", cena);
        cv.put("datums", datums);

        sqLiteDatabase.delete("cart", "user_id= ?", new String[]{String.valueOf(userId)});


        long result = sqLiteDatabase.insert("orders", null, cv);
        //te vel aiziet uz dzesanas funkciju no user_id un izdzest visus cart
        if (result == -1){
            return false;
        } else {
            return true;
        }

    }


//    public Boolean checkCart(String user_id, String prod_id){
//        SQLiteDatabase myDB = this.getWritableDatabase();
////        Cursor cursor = myDB.rawQuery("select * from cart where user_id = ? and prod_id = ?",new Integer[] {user_id,prod_id});
//
//        Integer uId = Integer.parseInt(user_id);
//        Integer pId = Integer.parseInt(prod_id);
//
////        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//        Cursor cursor = myDB.rawQuery("Select * from cart where user_id = ? and prod_id = ?", (uId,pId));//,new String[] {user_id,prod_id});
//        if (cursor.getCount()>0){
//            return true;
//        } else {
//            return false;
//        }
//    }

    public Boolean insertCartData(Integer prod_id, Integer user_id, String prod_name, String price, Integer amount){
//        SQLiteDatabase myDB = this.getWritableDatabase();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Boolean bo = checkCart(String.valueOf(user_id), String.valueOf(prod_id));
//        if (bo == true){
//            incCart(prod_id, user_id);
//        } else {
//        String pId = String.valueOf(prod_id);
//        String uId = String.valueOf(user_id);

        String am = getAmount(prod_id, user_id);
//        String pIds = String.valueOf(prod_id), uIds = String.valueOf(user_id);
        int a = Integer.parseInt(am);
        if (a>0) {
            incCart(prod_id,user_id, a);
        }else {
            ContentValues cv = new ContentValues();
            cv.put("prod_id", String.valueOf(prod_id));
            cv.put("user_id", String.valueOf(user_id));
            cv.put("prod_name", prod_name);
            cv.put("price", price);
            cv.put("amount", String.valueOf(amount));
            long result = sqLiteDatabase.insert("cart", null, cv);
//        }
        }

//        ContentValues cv = new ContentValues();
//        Double priceFloat = Double.parseDouble(price);//26.99;//Double.parseDouble(price);

//        if (result == -1){
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }

    public boolean insertProdData(){
//        long resu = -1;
        for (int i=101; i<151; i++) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            int prod_id = (100 + i);
            String prod_n = "Children's Product No. " + String.valueOf(i);
            boolean boole;
            String prod_price;
            if (i < 126) {
                prod_price = String.valueOf(Float.parseFloat("24.99") + i);
                boole = true;
            } else {
                prod_price = String.valueOf(Float.parseFloat("299.49") - i);
                boole = false;
            }
            String prod_desc = "Children's Product No. " + String.valueOf(i) + " is very comfortable and soft.";
            String prod_img = "Children" + String.valueOf(i) + ".png";

            ContentValues cv = new ContentValues();
            cv.put("id", prod_id);
            cv.put("name", prod_n);
            cv.put("special_offer", boole);
            cv.put("price", prod_price);
            cv.put("description", prod_desc);
            cv.put("image", prod_img);
            cv.put("category", "children");
            sqLiteDatabase.insert("product", null, cv);
               /* if (re != null) {
                    Toast.makeText(MainActivity.this, "Product Successfully Added to Your Cart", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Couldn't add this porduct to Your cart", Toast.LENGTH_SHORT).show();
                }*/
        }

        return true;
    }

    public Boolean checkemail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from user where email = ?",new String[] {email});
        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkpassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from user where email = ? and password = ?",new String[] {email,password});
        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public Cursor getDataa(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from product", null);
        return cursor;
    }

    public Cursor getDataUserId(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user", null);
        return cursor;
    }

    public Cursor getCartData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from cart", null);
        return cursor;
    }

    public Cursor getOrdersData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from orders", null);
        return cursor;
    }



    //var no si video: https://www.youtube.com/watch?v=9LYn-OBO5qE&t=191s

//    private static final String db_name = "AppDB";
//    private static final int db_version = 1;
//    private static final String tb_name = "product";

    //https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/
//    public ArrayList<Product> readProducts(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursorProducts = db.rawQuery("select * from product",null);
//        ArrayList<Product> productList = new ArrayList<>();
//
//        if(cursorProducts.moveToFirst()){
//            while (cursorProducts.moveToNext()){
//                cursorProducts.getString(2);
//                cursorProducts.getString(3);
//
//            }
//        }
//
//        cursorProducts.close();
//        return productList;
//    }


}
