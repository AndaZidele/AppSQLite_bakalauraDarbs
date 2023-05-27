package com.example.appsqlite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsqlite.R;

import java.util.ArrayList;

public class OrdersAdapter  extends RecyclerView.Adapter<OrdersAdapter.MyViewHolderOrder>{

    private Context context;
    private ArrayList user_id, user_name, email, phone, address, products_names, prod_ids, products_price, datums;

    public OrdersAdapter(Context context, ArrayList user_id, ArrayList user_name, ArrayList email, ArrayList phone, ArrayList address, ArrayList products_names,ArrayList prod_ids, ArrayList products_price, ArrayList datums){
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.products_names = products_names;
        this.prod_ids = prod_ids;
        this.products_price = products_price;
        this.datums = datums;
    }

    @NonNull
    @Override
    public OrdersAdapter.MyViewHolderOrder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.profile_orders_table_view, viewGroup, false);

        return new OrdersAdapter.MyViewHolderOrder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.MyViewHolderOrder myViewHolder, int i) {
//        myViewHolder.name.setText(productList.get(i).getName());
//        myViewHolder.price.setText(productList.get(i).getPrice());
//        myViewHolder.description.setText(productList.get(i).getDescription());

        myViewHolder.name.setText(String.valueOf(products_names.get(i)));//(orderList.get(i).getProd_names());
        myViewHolder.price.setText("Price: " + String.valueOf(products_price.get(i)));//orderList.get(i).getPrice() + " EUR");
        myViewHolder.datums.setText(String.valueOf(datums.get(i)));//(orderList.get(i).getDatums());

//        myViewHolder.price.setText((cartList.get(i).getPrice()).toString());
//        myViewHolder.amount.setText(String.valueOf(cartList.get(i).getAmount()));
    }


    @Override
    public int getItemCount() {
        return products_names.size();
    }


    public class MyViewHolderOrder extends RecyclerView.ViewHolder {
        CardView c_root_view;
        TextView name, price, datums;
        //
        public MyViewHolderOrder(@NonNull View itemView) {
            super(itemView);


            c_root_view = (CardView) itemView.findViewById(R.id.profile_root_view);
            name = (TextView) itemView.findViewById(R.id.order_txt_name);
            datums = (TextView) itemView.findViewById(R.id.order_txt_d);
            price = (TextView) itemView.findViewById(R.id.order_txt_price);
//            Retrofit retrofitUser = RetrofitUser.getInstance();
//            myAPI = retrofitUser.create(UserAPI.class);
        }
    }
}
