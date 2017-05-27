package com.techkid.anh82.randomquote.network.services;

import com.techkid.anh82.randomquote.network.RegisterRequest;
import com.techkid.anh82.randomquote.network.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by AnhLT on 5/27/2017.
 */

public interface LoginService {
    @POST("login")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
