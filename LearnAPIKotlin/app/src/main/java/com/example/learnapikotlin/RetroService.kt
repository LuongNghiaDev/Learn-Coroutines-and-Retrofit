package com.example.learnapikotlin

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    //https://gorest.co.in/public-api/users
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUsersList(): Call<UserList>

    //https://gorest.co.in/public-api/users?name=a
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>


    //https://gorest.co.in/public-api/users/121
    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<UserResponse>


    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer ca072a5f491492ea6fa2c3ab8e3c624d837d90ebfb11066e1472b0817806fc67")
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer ca072a5f491492ea6fa2c3ab8e3c624d837d90ebfb11066e1472b0817806fc67")
    fun updateUser(@Path("user_id") user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer ca072a5f491492ea6fa2c3ab8e3c624d837d90ebfb11066e1472b0817806fc67")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>

}