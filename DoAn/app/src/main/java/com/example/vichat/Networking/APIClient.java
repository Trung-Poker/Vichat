package com.example.vichat.Networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
<<<<<<< HEAD
    private static final String BASE_URL = "http://vichat.eastus.cloudapp.azure.com:8017";
=======
    private static final String BASE_URL = "http:localhost:8017";
>>>>>>> 270daf8ce4d49ceccca3592d6234d444cd3cbfc3

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
