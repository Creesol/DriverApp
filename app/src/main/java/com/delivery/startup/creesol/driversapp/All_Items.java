package com.delivery.startup.creesol.driversapp;



import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.delivery.startup.creesol.driversapp.callback.OrderCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class All_Items extends AppCompatActivity {
    private static final String TAG = "OrderDetail";
    private int DeliveryStatus;
    private List<OrderData> list;
    private String OrderId;
    private RecyclerView recyclerView;
    private String latitude;
    private String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__items);
        Intent intent=getIntent();
        //OrderId=intent.getStringExtra("OrderId");
        //latitude=Intent.getStringExtra("Latitude");
        //longitude=Intent.getStringExtra("Longitude");
        //OrderId="67";
        recyclerView = findViewById(R.id.RecyclerViewOrderDetail);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        //recyclerView.addItemDecoration(new EqualSpacingItemDecoration(1, EqualSpacingItemDecoration.VERTICAL));
        //recyclerView.addItemDecoration(new EqualSpacingItemDecoration(0, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        list=new ArrayList<>();
        getData(new OrderCallback() {
            @Override
            public void onSuccess(List<OrderData> list)  {
                RecyclerView.Adapter adapter = new orderDetailAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onError(String err) {

            }
        });




        //DeliveryStatus=2;
        Log.e("yeah", "onCreate: Runs" );



    }

    private void getData(final OrderCallback callback) {
        //String url=constant.getOrderDetailData+"?order_id="+OrderId;
        String url=constant.getAllItems;
        Log.e(TAG, "getData: IS CALLED" );
        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //String item=response.

                try {
                    JSONObject jsonObject=new JSONObject();
                    for(int i=0;i<response.length();i++) {

                        jsonObject = response.getJSONObject(i);
                        String product_name = jsonObject.getString("product_name");
                        String price = jsonObject.getString("price");
                        String quantity = jsonObject.getString("ItemCount");

                        list.add(new OrderData(product_name, price, quantity));
                    }

                    callback.onSuccess(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.e(TAG, "onResponse: "+response );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    /*public void Open(View view) {
        Log.e("YES", "Open: RUNNING" );
        //DeliveryStatus=1;
        sendDeliveryStatus();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    private void sendDeliveryStatus() {


        String url=constant.startDelivery+"?order_id="+OrderId;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("ok")){
                    //Dialog dialog=new Dialog();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




   /* public void Deliver(View view) {
        //Reached(OrderId);
        Reached();
        Intent intent =new Intent(orderDetail2.this,Payment.class);
        intent.putExtra("OrderId",OrderId);
        startActivity(intent);
    }

    private void Reached() {
        String url=constant.startDelivery+"?order_id="+OrderId;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Dialog dialog=new Dialog();
                Toast.makeText(getApplicationContext(), "Delivery Started Successfully", Toast.LENGTH_SHORT).show();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }*/
}
