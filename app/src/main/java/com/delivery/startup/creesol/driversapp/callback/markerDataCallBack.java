package com.delivery.startup.creesol.driversapp.callback;

import com.delivery.startup.creesol.driversapp.MarkerData;

import java.util.List;

public interface markerDataCallBack {
    void onSuccess(List<MarkerData> list);
    void onError(String err);
}
