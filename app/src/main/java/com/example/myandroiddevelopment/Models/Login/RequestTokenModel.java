package com.example.myandroiddevelopment.Models.Login;

import com.google.gson.annotations.SerializedName;

public class RequestTokenModel {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("expires_at")
    public String expires_at;
    @SerializedName("request_token")
    public String request_token;
}
