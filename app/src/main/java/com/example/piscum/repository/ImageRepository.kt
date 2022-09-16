package com.example.piscum.repository

import com.example.piscum.api.ApiService
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getImages(page :Int,limit: Int) =apiService.getImages(page,limit)

}