package com.delivery.startup.creesol.driversapp.callback;

import com.delivery.startup.creesol.driversapp.OrderData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface OrderCallback {
    void onSuccess(List<OrderData> list) ;
    void onError(String err);
}
