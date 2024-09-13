package com.bright.supreme.webservices;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    public static ApiServices apiServices;
    public static final String BASE_URL = "http://supremeagro.brightkidsplayway.co.in/";


    public static Retrofit getRetrofitClient(){
        if(retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            try {

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }catch (Exception e){
            }

        }
        return retrofit;
    }

    public static ApiServices getApiServices(){
        if (apiServices==null){
            apiServices = getRetrofitClient().create(ApiServices.class);
        }
        return apiServices;
    }
}
