package com.bura.githubrest.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface GitHubApi {

    //@GET("users/vikrorcz")
    //suspend fun getUser(username: String): Response<Username>

    @GET
    suspend fun getUser(@Url username: String): Response<Username>
}