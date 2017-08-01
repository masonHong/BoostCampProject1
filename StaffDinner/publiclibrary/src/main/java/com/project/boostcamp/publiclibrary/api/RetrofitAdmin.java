package com.project.boostcamp.publiclibrary.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public class RetrofitAdmin {
    private static RetrofitAdmin instance;
    private Retrofit retrofit;
    public AdminService adminService;
    public static RetrofitAdmin getInstance() {
        if(instance == null) {
            instance = new RetrofitAdmin();
        }
        return instance;
    }

    public RetrofitAdmin() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://220.230.122.122:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit.create(AdminService.class);
    }
}
