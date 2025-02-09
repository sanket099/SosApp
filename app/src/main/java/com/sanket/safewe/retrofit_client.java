package com.sanket.safewe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit_client {

    //private  static  final String base_url = "https://still-lake-87096.herokuapp.com/" ;//base url
    private  static  final String base_url = "https://soshack.azurewebsites.net/" ;//base url
    private static retrofit_client instance;
    private Retrofit retrofit; //retrofit object

    private retrofit_client(){ //constructor
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized retrofit_client getInstance(){
        if (instance == null){
            instance = new retrofit_client();
        }
        return instance;

    }
    public api getapi(){
        return retrofit.create(api.class);
    }
}
