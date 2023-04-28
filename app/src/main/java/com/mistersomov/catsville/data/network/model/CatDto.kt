package com.mistersomov.catsville.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("url")
    @Expose
    val url: String? = null,
)


