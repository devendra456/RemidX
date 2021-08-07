package com.skyview.remidx.network_request;

import com.skyview.remidx.model_class.DetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("VechileList")
    Call<String> getAllData();
    @GET("DriversList")
    Call<String> getDrivers();
}
