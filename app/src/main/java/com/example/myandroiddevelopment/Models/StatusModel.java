package com.example.myandroiddevelopment.Models;

import com.google.gson.annotations.SerializedName;

public class StatusModel {
    @SerializedName("status_code")
    public Integer status_code;
    @SerializedName("status_message")
    public String status_message;
}
