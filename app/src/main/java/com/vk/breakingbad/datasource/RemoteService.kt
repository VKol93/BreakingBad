package com.vk.breakingbad

import com.vk.breakingbad.data.Character
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {
    @GET("/api/characters")
    suspend fun getCharacter(): List<Character>
}
object BreakingBadAPI {
    private val BASE_URL = "https://breakingbadapi.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val RETROFIT_SERVICE: API = retrofit.create(API::class.java)

}