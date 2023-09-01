package com.example.projectmanagementapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getTasks.php")
    Call<List<Task>> getTask(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );

}
