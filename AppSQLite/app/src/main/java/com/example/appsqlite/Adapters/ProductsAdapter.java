package com.example.appsqlite.Adapters;


import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsqlite.OneProductActivity;
import com.example.appsqlite.R;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

//    List<Product> productList;
    private Context context; //Android content
    private ArrayList name, desc, price, id, image;// id;
    public ProductsAdapter(Context context, ArrayList name, ArrayList price, ArrayList desc, ArrayList id, ArrayList image) {
        this.context = context;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.id = id;
        this.image = image;
    }

    /*Context context; //Android content
    ArrayList<Product> productList;
    public ProductsAdapter(Context context, ArrayList<Product> productList){
        this.context = context;
        this.productList = productList;
    }*/

//    public ProductsAdapter(List<Product> productList) {this.productList = productList;}

   // CompositeDisposable compositeDisposable = new CompositeDisposable();
    //UserAPI myAPI;


//    Intent inte = getIntent();
//
//    private Intent getIntent() {
//        return inte;
//    }
//
//    String epastins = inte.getStringExtra("thisUsersEmail");// lietotaja epasts
//


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_table_view, viewGroup, false);

        View itemView = LayoutInflater.from(context).inflate(R.layout.products_table_view,viewGroup,false);

        //inflate(R.layout.products_table_view,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(String.valueOf(name.get(i)));
        myViewHolder.description.setText(String.valueOf(desc.get(i)));

        myViewHolder.price.setText(String.valueOf(price.get(i)));

//        myViewHolder.name.setText(productList.get(i).getName());

        myViewHolder.itemView.setOnClickListener((v->{
            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), OneProductActivity.class);
            //String str = myMovieDataList.getMovieName().toString();
            cardViewsActivity.putExtra("oneName",  String.valueOf(name.get(i)));
            cardViewsActivity.putExtra("onePrice",  String.valueOf(price.get(i)));
            cardViewsActivity.putExtra("oneDescr",  String.valueOf(desc.get(i)));
            cardViewsActivity.putExtra("oneId",  String.valueOf(id.get(i)));
//            cardViewsActivity.putExtra("user_email",  user_email)
//            cardViewsActivity.putExtra("oneImage",  productList.get(i).getImage());
//            String iToS = Integer.toString(productList.get(i).getId());
//            cardViewsActivity.putExtra("id", iToS);

            myViewHolder.name.getContext().startActivity(cardViewsActivity);

        }));


//        myViewHolder.name.setText(String.valueOf(productList.get(i).getName()));
//        myViewHolder.price.setText(String.valueOf(productList.get(i).getPrice()));
//        myViewHolder.description.setText(String.valueOf(productList.get(i).getDescription()));

       /* myViewHolder.itemView.setOnClickListener((v->{
            Intent cardViewsActivity = new Intent(myViewHolder.name.getContext(), OneProductActivity.class);
            //String str = myMovieDataList.getMovieName().toString();
            cardViewsActivity.putExtra("oneName",  productList.get(i).getName());
            cardViewsActivity.putExtra("onePrice",  productList.get(i).getPrice());
            cardViewsActivity.putExtra("oneDescr",  productList.get(i).getDescription());
            cardViewsActivity.putExtra("oneId",  productList.get(i).getId());
            myViewHolder.name.getContext().startActivity(cardViewsActivity);
        }));*/

    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        CardView root_view;
        TextView name, price, description; //vel pievienot visus
        MaterialButton btnEdt, btnDelete;
        //        String epastins = ProductsActivity.class.getName(userEmail);
        Layout userEmailLayout;
        TextView userEmail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


//            root_view = (CardView) itemView.findViewById(R.id.root_view);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            description = (TextView) itemView.findViewById(R.id.txt_desc);

        }
    }
}
