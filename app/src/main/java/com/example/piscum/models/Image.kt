package com.example.piscum.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    var id: String,
    var author: String,
    var width: Int,
    var height: Int,
    var url: String,
    var download_url: String,
    var blur: String,
    var grayscale: String
) : Parcelable
