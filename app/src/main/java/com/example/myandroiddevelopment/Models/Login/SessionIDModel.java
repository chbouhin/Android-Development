package com.example.myandroiddevelopment.Models.Login;

import com.google.gson.annotations.SerializedName;

public class SessionIDModel {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("session_id")
    public String session_id;
}
