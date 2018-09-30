package com.delivery.startup.creesol.driversapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.delivery.startup.creesol.driversapp.OrderData;
import com.delivery.startup.creesol.driversapp.R;
import com.delivery.startup.creesol.driversapp.allOrdersAdapter;
import com.delivery.startup.creesol.driversapp.callback.OrderCallback;
import com.delivery.startup.creesol.driversapp.constant;
import com.delivery.startup.creesol.driversapp.getDataFromServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AllOrders extends AppCompatActivity {
    LinearLayout order_empty;
    LinearLayout onGoingOrder;
    CardView orderHistory;
    private int states;
    private TextView order_id;
    private TextView order_date;
    private TextView order_items;
    private TextView  total_Price;
    private RelativeLayout start;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter2;
    private LinearLayout goneButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);
        order_empty = findViewById(R.id.order_empty);

        //recyclerView=findViewById(R.id.OrderRecyclerView);
        recyclerView2=findViewById(R.id.OnGoingOrderRecyclerView);
        onGoingOrder = findViewById(R.id.OnGoingOrder);
        //orderHistory = findViewById(R.id.OrderHistory);
        start=findViewById(R.id.OrderStart);
        order_id=findViewById(R.id.orderNo);
        order_date=findViewById(R.id.deliveryDate);
        order_items=findViewById(R.id.orderItems);
        total_Price=findViewById(R.id.cost);

        //recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new EqualSpacingItemDecoration(2, EqualSpacingItemDecoration.HORIZONTAL));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize(true);
        //recyclerView2.addItemDecoration(new EqualSpacingItemDecoration(2, EqualSpacingItemDecoration.VERTICAL));
        //recyclerView.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));




        getDataFromServer.getOrderDataOnGoing(getApplicationContext(),constant.getAllOrders, new OrderCallback() {
            @Override
            public void onSuccess(List<OrderData> list) {
                start.setVisibility(View.GONE);
                onGoingOrder.setVisibility(View.VISIBLE);
                //goneButton.setVisibility(View.VISIBLE);
                //orderHistory.setVisibility(View.VISIBLE);

               /* order_id.setText(list.get(0).getOrder_id());
                order_date.setText(list.get(0).getOrder_date());
                order_items.setText(list.get(0).getOrder_Items());
                total_Price.setText(list.get(0).getTotal_Price());
                delivery_before.setText(list.get(0).getDelivery_before());
                */

                // adapter=new OrderAdapter(getApplicationContext(),list);
                //recyclerView.setAdapter(adapter);
                adapter2=new allOrdersAdapter(getApplicationContext(),list);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onError(String message) {
                // start.setVisibility(View.GONE);
                onGoingOrder.setVisibility(View.GONE);
                order_empty.setVisibility(View.VISIBLE);

            }


        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void start_shopping(View view) {
        finish();
    }

    public void ActivateOrderHistory(View view) {


    }
}
