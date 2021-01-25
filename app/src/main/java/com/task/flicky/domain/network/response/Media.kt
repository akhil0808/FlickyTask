package com.task.flicky.domain.network.response


import com.google.gson.annotations.SerializedName


data class Media(
    @SerializedName("m")
    var imageUrl: String = ""
)