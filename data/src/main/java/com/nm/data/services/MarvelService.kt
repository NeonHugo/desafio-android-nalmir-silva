package com.nm.data.services


import com.nm.data.model.CharactersResponse
import com.nm.data.model.ComicsResponse
import com.nm.infrastructure.net.RetrofitBuild.NO_AUTHENTICATION_HEADER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    @Headers(NO_AUTHENTICATION_HEADER)
    suspend fun getCharacters(
        @Query("ts") ts: String? = null,
        @Query("apikey") apikey: String? = null,
        @Query("hash") hash: String? = null,
        @Query("offset") offset: String? = null,
        @Query("limit") limit: String? = null
    ): Response<CharactersResponse>

    @GET("v1/public/characters/{characterId}/comics")
    @Headers(NO_AUTHENTICATION_HEADER)
    suspend fun getComics(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String? = null,
        @Query("apikey") apikey: String? = null,
        @Query("hash") hash: String? = null
    ): Response<ComicsResponse>

}
