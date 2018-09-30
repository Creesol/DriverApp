package com.delivery.startup.creesol.driversapp.callback;

import com.delivery.startup.creesol.driversapp.loginData;

import java.util.ArrayList;
import java.util.List;

public interface loginDatacallback {
    void onSuccess(ArrayList<String> list);
    void onError(String err);
}
