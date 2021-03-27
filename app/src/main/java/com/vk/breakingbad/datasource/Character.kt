package com.vk.breakingbad.datasource

import com.google.gson.annotations.SerializedName

data class Character (
    @SerializedName(value = "char_id") var id:Int,
    var name: String,
    var birthday: String,
    var info: String, //type??
    @SerializedName(value = "img") var image: String,
    var status: String,
    var nickname: String,
    @SerializedName(value = "appearance") var season: List<String>,
    var portrayed: String,
    var category: String
)
