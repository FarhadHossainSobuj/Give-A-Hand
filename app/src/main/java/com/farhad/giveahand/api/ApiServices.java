package com.farhad.giveahand.api;

import com.farhad.giveahand.models.Areas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("areas")
    Call<Areas> getAreas();


}
