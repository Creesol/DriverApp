package com.delivery.startup.creesol.driversapp.callback;

public interface getPaymentDataCallBack {
    void onSuccess(String total);
    void onError(String err);
}
