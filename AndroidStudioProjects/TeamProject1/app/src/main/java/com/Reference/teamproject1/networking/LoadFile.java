package com.Reference.teamproject1.networking;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LoadFile {

    @GET("output/out.csv")
    Call<ResponseBody> getFile();
}