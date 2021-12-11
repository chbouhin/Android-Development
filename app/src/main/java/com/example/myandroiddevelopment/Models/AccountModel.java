package com.example.myandroiddevelopment.Models;

import com.google.gson.annotations.SerializedName;

public class AccountModel {
    @SerializedName("avatar")
    public AvatarModel avatar;
    @SerializedName("id")
    public Integer id;
    @SerializedName("iso_639_1")
    public String iso_639_1;
    @SerializedName("iso_3166_1")
    public String iso_3166_1;
    @SerializedName("name")
    public String name;
    @SerializedName("include_adult")
    public Boolean include_adult;
    @SerializedName("username")
    public String username;
}
