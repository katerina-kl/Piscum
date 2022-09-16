package com.example.piscum.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.piscum.models.Image
import com.example.piscum.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel(){

    private val _response = MutableLiveData<List<Image>>()
    val responseImage : LiveData<List<Image>>
        get() = _response


    fun getAllImages(page: Int, limit: Int) = viewModelScope.launch {
        repository.getImages(page, limit).let { response ->

            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("tag", "getAllImages: Error: ${response.code()}")
            }

        }
    }

}