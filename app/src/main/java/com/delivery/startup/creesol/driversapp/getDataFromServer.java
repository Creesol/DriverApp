package com.delivery.startup.creesol.driversapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.delivery.startup.creesol.driversapp.callback.OrderCallback;
import com.delivery.startup.creesol.driversapp.callback.loginCallBack;
import com.delivery.startup.creesol.driversapp.callback.loginDatacallback;
import com.delivery.startup.creesol.driversapp.callback.markerDataCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class getDataFromServer {
    private List<MarkerData> markerDataList;
    public static void getMarkerData(String url, String date,Context context,markerDataCallBack callBack){

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String order_id=null;
                String langitude=null;
                String longitude=null;
                JSONObject js=new JSONObject();
            for(int i=0;i<response.length();i++){
                try {
                    js=response.getJSONObject(i);
                    order_id=js.getString("order_id");
                    langitude=js.getString("langitude");
                    longitude=js.getString("longitude");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }
    public static void getAdminLoginData(Context context, final loginDatacallback callback){

        String url=constant.getAdminLogindata;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String title=null;
                ArrayList<String> list;
                list=new ArrayList<>();

                JSONObject js=new JSONObject();
                for(int i=0;i<response.length();i++){
                    try {
                        js=response.getJSONObject(i);
                        title=js.getString("name");
                        list.add(title);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.onSuccess(list);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }


    public static void getOrderDataOnGoing(Context context,String url, final OrderCallback callback){


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

               List<OrderData> list;
                list=new ArrayList<>();

                JSONObject js=new JSONObject();
                for(int i=0;i<response.length();i++){
                    try {
                        js=response.getJSONObject(i);

                        String item_id=js.getString("order_id");
                        String orderItems=js.getString("timed");
                        String deliverybefore=js.getString("dated");
                        String total_Price=js.getString("total_price");


                        list.add(new OrderData(item_id,orderItems,deliverybefore,total_Price));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.onSuccess(list);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }


    public static void adminTrueOrFalse(Context context, String name, String pass, final loginCallBack callBack) {
        String url=constant.getAdminLoginTruthOrFalse+"?name="+name+"&pass="+pass;

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Dialog dialog=new Dialog();
                //Intent intent=new Intent(Payment.this,MainActivity.class);
                //startActivity(intent);

                try {
                    String admin_id=response.getString("admin_id");
                    if (response.getString("total").equals("1")) {
                        callBack.onSuccess(admin_id);
                    }
                    else{
                        callBack.notSuccess("false");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError("error");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static void postAdminToken(Context context, String admin_id, String token, final loginCallBack callBack) {
        String url=constant.postAdminToken+"?admin_id="+admin_id+"&token="+token;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Dialog dialog=new Dialog();
                //Intent intent=new Intent(Payment.this,MainActivity.class);
                //startActivity(intent);

                    callBack.onSuccess("1");



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError("error");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
