package com.smita.engineeraitest.services;

import com.smita.engineeraitest.utils.AppApis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient(){

        if(retrofit==null){

            retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppApis.BASE_URL)
                    .build();
        }

        return retrofit;
    }

}
