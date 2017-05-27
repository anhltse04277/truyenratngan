package com.techkid.anh82.randomquote.network.services;

import com.techkid.anh82.randomquote.network.RegisterRequest;
import com.techkid.anh82.randomquote.network.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by QuanT on 5/27/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
