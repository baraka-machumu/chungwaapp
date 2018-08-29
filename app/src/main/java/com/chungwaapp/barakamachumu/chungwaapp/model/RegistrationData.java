package com.chungwaapp.barakamachumu.chungwaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationData {

    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public RegistrationData withSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
