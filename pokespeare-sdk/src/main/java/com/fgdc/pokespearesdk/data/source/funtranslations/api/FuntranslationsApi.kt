package com.fgdc.pokespearesdk.data.source.funtranslations.api

import com.fgdc.pokespearesdk.data.source.funtranslations.entity.FuntranslationsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FuntranslationsApi {
    companion object {
        private const val SHAKESPEARE_ENDPOINT = "shakespeare"
    }

    @FormUrlEncoded
    @POST(SHAKESPEARE_ENDPOINT)
    suspend fun getTextTranslated(@Field("text") text: String): Response<FuntranslationsResponse>
}
