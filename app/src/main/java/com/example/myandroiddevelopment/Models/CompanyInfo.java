package com.example.myandroiddevelopment.Models;

import com.google.gson.annotations.SerializedName;

public class CompanyInfo {
    @SerializedName("id")
    public Integer id;
    @SerializedName("logo_path")
    public String logo_path;
    @SerializedName("name")
    public String name;
    @SerializedName("origin_country")
    public String origin_country;
}
