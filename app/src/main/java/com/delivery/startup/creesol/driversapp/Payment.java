package com.delivery.startup.creesol.driversapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.delivery.startup.creesol.driversapp.callback.getPaymentDataCallBack;

public class Payment extends AppCompatActivity {
 private EditText rate;
 private String OrderId;
 private TextView total_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent=getIntent();
       OrderId=intent.getStringExtra("OrderId");
        rate=findViewById(R.id.Rate);
        total_amount=findViewById(R.id.Total_amount);
        getTotalPrice(new getPaymentDataCallBack() {
            @Override
            public void onSuccess(String total) {
                total_amount.setText(total);

            }
            @Override
            public void onError(String err){
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getTotalPrice(final getPaymentDataCallBack callBack){
        String url=constant.getOrderPaymentData+"?order_id="+OrderId;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

             callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError("There is an error connecting to server");

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    public void PaymentConfirm(View view) {
        String getRate=rate.getText().toString();
        String url=constant.confirmPayment+"?order_id="+OrderId;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    //Dialog dialog=new Dialog();
                    Intent intent=new Intent(Payment.this,MainActivity.class);
                    startActivity(intent);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}
