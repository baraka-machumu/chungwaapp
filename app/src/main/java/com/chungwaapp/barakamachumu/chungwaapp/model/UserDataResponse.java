package com.chungwaapp.barakamachumu.chungwaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataResponse {

    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("seed")
    @Expose
    public Integer seed;
    @SerializedName("token")
    @Expose
    public String token;
//
//    public Integer getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(Integer success) {
//        this.success = success;
//    }
//
//    public UserDataResponse withSuccess(Integer success) {
//        this.success = success;
//        return this;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public UserDataResponse withMessage(String message) {
//        this.message = message;
//        return this;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public UserDataResponse withId(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public UserDataResponse withEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public UserDataResponse withPhone(String phone) {
//        this.phone = phone;
//        return this;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public UserDataResponse withUsername(String username) {
//        this.username = username;
//        return this;
//    }
//
//    public Integer getSeed() {
//        return seed;
//    }
//
//    public void setSeed(Integer seed) {
//        this.seed = seed;
//    }
//
//    public UserDataResponse withSeed(Integer seed) {
//        this.seed = seed;
//        return this;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public UserDataResponse withToken(String token) {
//        this.token = token;
//        return this;
//    }

}