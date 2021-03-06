package com.vk.breakingbad.data

import com.google.gson.annotations.SerializedName

data class Character (
    @SerializedName(value = "char_id") var id:Int,
    var name: String,
    @SerializedName(value = "img") var image: String,
    var status: String,
    var nickname: String,
    @SerializedName(value = "appearance") var season: List<String>,
    var occupation:List<String>
)
