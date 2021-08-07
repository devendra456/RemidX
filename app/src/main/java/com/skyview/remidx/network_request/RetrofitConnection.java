package com.skyview.remidx.network_request;

import android.content.Context;

import org.json.JSONException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitConnection {

    private static final String baseUrl="http://reminderappforbansal.onevisit.in/Apis/";

     public static RetrofitConnection getInstance(){
         RetrofitConnection retrofitConnection=null;
         if(retrofitConnection==null){
             retrofitConnection= new RetrofitConnection();
         }
         return retrofitConnection;
     }

     public Api getApiClient(){
         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
         Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create())
                 .client(client)
                 .build();
                 return retrofit.create(Api.class);
     }

     public void callApiResponse(Context context, Call<String> call,CallBackRetrofit callBackRetrofit,String action){
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 try {
                     callBackRetrofit.resposeResult(response.body(), true,action);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 try {
                     callBackRetrofit.resposeResult(t.getMessage(), true,action);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         });
     }

     public interface CallBackRetrofit{
        public void resposeResult(String s,Boolean b,String action) throws JSONException;
     }
}
