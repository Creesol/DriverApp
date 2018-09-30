package com.delivery.startup.creesol.driversapp.callback;

public interface loginCallBack {
    void onSuccess(String msg);
    void notSuccess(String msg);
    void onError(String err);
}
