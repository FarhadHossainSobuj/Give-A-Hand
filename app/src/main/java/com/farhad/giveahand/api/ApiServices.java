package com.farhad.giveahand.api;

import com.farhad.giveahand.models.Areas;
import com.farhad.giveahand.models.Post;
import com.farhad.giveahand.models.ResponseResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @GET("areas")
    Call<Areas> getAreas();

    @POST("help-request")
    Call<ResponseResult> requestPost(@Body Post post);


}
