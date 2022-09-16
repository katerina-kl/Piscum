package com.example.piscum.api

import com.example.piscum.helper.Constants
import com.example.piscum.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.LIST_IMAGES)
    suspend fun getImages(@Query("page") page :Int, @Query("limit") limit: Int): Response<ImageResponse>

}