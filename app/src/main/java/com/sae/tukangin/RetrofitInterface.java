package com.sae.tukangin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("register")
    Call<User> registerUser(@Body User user);
}
