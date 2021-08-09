package com.skyview.remidx.network_request;

import com.skyview.remidx.model_class.DetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Api {
    @GET("VechileList")
    Call<String> getAllData();
    @GET("DriversList")
    Call<String> getDrivers();

    @FormUrlEncoded
    @POST("VechileAdd")
    Call<String> addVehicle(
            @Field("ownername") String ownername,
            @Field("vehiclenumber") String vehiclenumber,
            @Field("mobileno") String mobileno,
            @Field("insurancecompany") String insurancecompany,
            @Field("insu_fr_date") String insu_fr_date,
            @Field("insu_to_date") String insu_to_date,
            @Field("permit_gl_fr_date") String permit_gl_fr_date,
            @Field("permit_gl_to_date") String permit_gl_to_date,
            @Field("permit_st_fr_date") String permit_st_fr_date,
            @Field("permit_st_to_date") String permit_st_to_date,
            @Field("puc_fr_date") String puc_fr_date,
            @Field("puc_to_date") String puc_to_date,
            @Field("tax_fr_date") String tax_fr_date,
            @Field("tax_to_date") String tax_to_date,
            @Field("fitness_from") String fitness_from,
            @Field("fitness_to") String fitness_to
    );

    @FormUrlEncoded
    @POST("VechileDelete")
    Call<String> deleteData(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("DriverAdd")
    Call<String> addDriver(@Field("name") String name,
                           @Field("phone") String mobile,
                           @Field("address") String address,
                           @Field("aadhar") String aadhar,
                           @Field("pancard") String pancard,
                           @Field("dl_no") String dl_no,
                           @Field("dob") String dob,
                           @Field("valid_date") String valid_date
                           );

    @FormUrlEncoded
    @POST("DriverDelete")
    Call<String> deleteDriver(@Field("id") String id);
}
