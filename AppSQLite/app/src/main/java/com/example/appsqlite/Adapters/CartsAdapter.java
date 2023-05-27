package com.example.appsqlite.Adapters;


import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsqlite.CartActivity;
import com.example.appsqlite.Helpers.DBHelper;
import com.example.appsqlite.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.MyViewHolderCart>{

//    public static Context MyViewHolderCart;
    private Context context;
    private ArrayList prod_name, price, amount, prod_id, user_id;
//    List<Cart> cartList;
    String epa;
//    DBHelperForCart myDBforCart;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    DBHelper myDB;


    //    public CartsAdapter(List<Cart> cartList, String epa) {this.cartList = cartList; this.epa = epa;}
//    public CartsAdapter(Context context, String epa, Cursor cursor, ArrayList name, ArrayList desc, ArrayList price, ArrayList amount, ArrayList prod_id, ArrayList user_id){
    public CartsAdapter(Context context, DBHelper myDB,  ArrayList prod_id, ArrayList user_id, ArrayList prod_name, ArrayList price, ArrayList amount){
        this.context = context;
        this.myDB = myDB;
     //   this.epa = epa;
     //   this.cursor = cursor;
      //  this.name = name;
        this.price = price;
        this.prod_name = prod_name;
//        this.desc = desc;
        this.amount = amount;
        this.prod_id = prod_id;
        this.user_id = user_id;
    }


    @NonNull
    @Override
    public MyViewHolderCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_table_view, viewGroup, false);

        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_table_view,viewGroup,false);

        return new MyViewHolderCart(itemView);
    }

//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//
//    }

        @Override
    public void onBindViewHolder(@NonNull MyViewHolderCart myViewHolder, int i) {
//        myViewHolder.name.setText(productList.get(i).getName());
//        myViewHolder.price.setText(productList.get(i).getPrice());
//        myViewHolder.description.setText(productList.get(i).getDescription());


        myViewHolder.name.setText(String.valueOf(prod_name.get(i)));
//        myViewHolder.description.setText(String.valueOf(desc.get(i)));
        myViewHolder.amount.setText(String.valueOf(amount.get(i)));
        myViewHolder.price.setText(String.valueOf(price.get(i)));

        String prIdSt = String.valueOf(prod_id.get(i));
        String usIdSt = String.valueOf(user_id.get(i));
        String amo = String.valueOf(amount.get(i));



        myViewHolder.btnPlus.setOnClickListener((v->{
            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), CartActivity.class);

            //funkcija kur skaitu palielina pa 1


            int prod_id = Integer.parseInt(prIdSt);//String.valueOf(cartList.get(i).getId());
            int user_id = Integer.parseInt(usIdSt);//parseInt(String.valueOf(user_id.get(i)));
            int a = Integer.parseInt(amo);
//            getUsersIdInc(prod_id);//vrb te jau epa likt

            incProduct(prod_id, user_id, a);
//            cardViewsActivity.putExtra("name",  productList.get(i).getName());
//            String iToS = Integer.toString(productList.get(i).getId());
//            cardViewsActivity.putExtra("id", iToS);
            //vajadzetu reloadoties sim skatam ja nesanak, tad tikai ka seit pamaina skaitu un kopejo cenu
            myViewHolder.name.getContext().startActivity(cardViewsActivity);
        }));

        myViewHolder.btnMinus.setOnClickListener((v->{
            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), CartActivity.class);

            //1)sakuma dabut produktsa id no tabulas product
            //2) tad usera id no tabulas user
//            if ((cartList.get(i).getAmount()) == 1){
//            deleteProduct( "8", "1");
//            } else {

            //funkcija kur skaitu samazina pa 1
            int prod_id = Integer.parseInt(prIdSt);//String.valueOf(cartList.get(i).getId());
            int user_id = Integer.parseInt(usIdSt);//parseInt(String.valueOf(user_id.get(i)));
            int a = Integer.parseInt(amo);
//            getUsersIdInc(prod_id);//vrb te jau epa likt

            if (a==1){
                deleteProduct(prod_id, user_id);
            }else {
                decProduct(prod_id, user_id, a);
            }
//            }



            // bet ja amount jau ir 1 tad paradas pazinjojuma logs vai
            // velaties dzest so produktu no groza un ja ja
            //tad aiziet uz deleteProduct funkciju

//            cardViewsActivity.putExtra("name",  productList.get(i).getName());
//            String iToS = Integer.toString(productList.get(i).getId());
//            cardViewsActivity.putExtra("id", iToS);
            //vajadzetu reloadoties sim skatam ja nesanak, tad tikai ka seit pamaina skaitu un kopejo cenu
            myViewHolder.name.getContext().startActivity(cardViewsActivity);
        }));


//            myViewHolder.btnEdt.setOnClickListener((v->{
//            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), OneProductActivity.class);
//            //String str = myMovieDataList.getMovieName().toString();
//            cardViewsActivity.putExtra("name",  productList.get(i).getName());
//            String iToS = Integer.toString(productList.get(i).getId());
//            cardViewsActivity.putExtra("id", iToS);
//            myViewHolder.name.getContext().startActivity(cardViewsActivity);
//        }));

        myViewHolder.btnDel.setOnClickListener((v->{
            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), CartActivity.class);
//            String iToS = Integer.toString(productList.get(i).getId());
//            cardViewsActivity.putExtra("id", iToS);
//            cardViewsActivity.putExtra("name",  productList.get(i).getName());
            int prod_id = Integer.parseInt(prIdSt);//String.valueOf(cartList.get(i).getId());
            int user_id = Integer.parseInt(usIdSt);//parseInt(String.valueOf(user_id.get(i)));
//            getUsersIdInc(prod_id);//vrb te jau epa likt

            deleteProduct(prod_id, user_id);
            myViewHolder.name.getContext().startActivity(cardViewsActivity);
        }));



    }


    @Override
    public int getItemCount() {
        return prod_name.size();
    }


    public class MyViewHolderCart extends RecyclerView.ViewHolder {
        CardView c_root_view;
        TextView name, price, amount; //vel pievienot visus
        MaterialButton btnPlus, btnMinus, btnDel;
//        ArrayList<String> prod_id, user_id;
        //
        public MyViewHolderCart(@NonNull View itemView) {
            super(itemView);

//            myDB = new DBHelper(this);


//            c_root_view = (CardView) itemView.findViewById(R.id.cart_root_view);
            name = (TextView) itemView.findViewById(R.id.cart_product_name);
            price = (TextView) itemView.findViewById(R.id.cart_product_price);
            amount = (TextView) itemView.findViewById(R.id.cart_product_amount);

            btnMinus = (MaterialButton) itemView.findViewById(R.id.btn_minuss);
            btnPlus = (MaterialButton) itemView.findViewById(R.id.btn_plus);
            btnDel = (MaterialButton) itemView.findViewById(R.id.btn_delete_product);

//            myDBforCart = new DBHelperForCart(this);
//            Cursor cursor =


//            Retrofit retrofitUser = RetrofitUser.getInstance();
//            myAPI = retrofitUser.create(UserAPI.class);
//
//            Retrofit retrofit = RetrofitProduct.getInstance();
//            myAPIget = getAPI();//retrofit.create(UserAPI.class);
        }
    }

    private void incProduct(int product, int user, int am){
        sqLiteDatabase = myDB.getWritableDatabase();
        Boolean getRes = myDB.incCart(product, user, am);
//        compositeDisposable.add(myAPI.incProduct(product, user)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                    }
//                }));


    }

    private void decProduct(int product, int user, int amo){
        sqLiteDatabase = myDB.getWritableDatabase();
        int am = amo - 1;
        Boolean getRes = myDB.decCart(product, user, am);
//        compositeDisposable.add(myAPI.decProduct(product, user)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
////                        Toast.makeText(CartsAdapter.this, ""+s, Toast.LENGTH_SHORT).show();
//                    }
//                }));


    }

    private void deleteProduct(int product, int user){
        sqLiteDatabase = myDB.getWritableDatabase();
        Boolean getRes = myDB.delCart(product, user);
//        compositeDisposable.add(myAPI.deleteProduct(product, user)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
////                        Toast.makeText(CartsAdapter.this, ""+s, Toast.LENGTH_SHORT).show();
//                    }
//                }));


    }


    private void getUsersIdInc(String product){

        //)int prod_id,int amount, String name, float price, String user_email)
       /* compositeDisposable.add(myAPIget.getUserList()
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
                                           String uId = String.valueOf(userID);
                                           incProduct(product, uId);

                                       }
//                                       Toast.makeText(OneProductActivity.this, "Te!!!", Toast.LENGTH_SHORT).show();


//                                       cenaKopa = cenaKopa + cenaVienam;
                                   }

//                                   adapter = new ProductsAdapter(people);
//                                   recycler_search.setAdapter(adapter);

//                                   int produkta_id = people.get(2).getId();
//                                   Toast.makeText(ProductsActivity.this, "TE: " + produkta_id, Toast.LENGTH_SHORT).show();



                               }


                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
//                                   Toast.makeText(CartActivity.this, "Not found from All Products" , Toast.LENGTH_SHORT).show();
                               }
                           }
                ));*/
    }


}
