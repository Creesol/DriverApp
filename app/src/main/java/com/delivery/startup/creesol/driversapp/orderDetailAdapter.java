package com.delivery.startup.creesol.driversapp;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class orderDetailAdapter extends RecyclerView.Adapter<orderDetailAdapter.ViewHolder>{
    private List<OrderData> list;
    Context context;
    public orderDetailAdapter(Context context, List<OrderData> objects){
        this.context = context;
        this.list=objects;
    }


    @NonNull
    @Override
    public orderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.orderitems,parent,false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull orderDetailAdapter.ViewHolder holder, int position) {
        holder.productName.setText(list.get(position).getItemName());
        holder.orderItems.setText(list.get(position).getQuantity());
        holder.cost.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName, orderItems,cost;



        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            productName= itemView.findViewById(R.id.productName);
            orderItems = itemView.findViewById(R.id.Quantity);

            cost = itemView.findViewById(R.id.Rate);
        }
    }

}