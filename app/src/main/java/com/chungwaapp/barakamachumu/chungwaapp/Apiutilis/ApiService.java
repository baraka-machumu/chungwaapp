package com.chungwaapp.barakamachumu.chungwaapp.Apiutilis;

import com.chungwaapp.barakamachumu.chungwaapp.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("user/register")
    Call<RegisterResponse> userRegister(@Field("email") String email,
                                        @Field("verification_code") String verification_code);


}
