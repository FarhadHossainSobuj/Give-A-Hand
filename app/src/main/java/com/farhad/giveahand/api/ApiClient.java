package com.farhad.giveahand.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit=null;
    public static final String BASE_URL="http://giveahandbd.com/apps/api/v1/";
    public static  Retrofit getApiClient(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
