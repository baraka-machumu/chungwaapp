package com.chungwaapp.barakamachumu.chungwaapp.ApiUtilis;

import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;
import com.chungwaapp.barakamachumu.chungwaapp.model.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("user/register")
    Call<RegisterResponse> userRegister(@Field("email") String email);

    @FormUrlEncoded
    @POST("user/login")
    Call<UserDataResponse> userLogin(@Field("email") String email,@Field("password") String password);

}

