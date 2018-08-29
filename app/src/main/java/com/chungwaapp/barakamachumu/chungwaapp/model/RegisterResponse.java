package com.chungwaapp.barakamachumu.chungwaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("massage")
    @Expose
    private String massage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public RegisterResponse withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public RegisterResponse withMassage(String massage) {
        this.massage = massage;
        return this;
    }

}