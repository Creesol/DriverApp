package com.delivery.startup.creesol.driversapp;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;



public class allOrdersAdapter extends RecyclerView.Adapter<allOrdersAdapter.ViewHolder>{
    private List<OrderData> list;
    Context context;
    public allOrdersAdapter(Context context, List<OrderData> objects){
        this.context = context;
        this.list=objects;
    }


    @NonNull
    @Override
    public allOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.orders_history,parent,false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull allOrdersAdapter.ViewHolder holder, int position) {
        holder.orderNo.setText(list.get(position).getItem_id());
        holder.orderItems.setText(list.get(position).getOrder_Items());
        holder.deliveryDate.setText(list.get(position).getDelivery_before());
        holder.cost.setText(list.get(position).getTotal_Price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderNo, orderItems, deliveryDate,cost;
         CardView cardClick;
        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            orderNo = itemView.findViewById(R.id.orderNo);
            orderItems = itemView.findViewById(R.id.orderItems);
            deliveryDate = itemView.findViewById(R.id.deliveryDate);
            cost = itemView.findViewById(R.id.cost);
            cardClick=itemView.findViewById(R.id.CardOrdersClick);
            cardClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),orderDetail2.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("OrderId",list.get(getLayoutPosition()).getItem_id());
                    Log.e("Adapter", "onClick: "+list.get(getLayoutPosition()).getItem_id());

                    view.getContext().startActivity(intent);

                }
            });

        }
    }

}

