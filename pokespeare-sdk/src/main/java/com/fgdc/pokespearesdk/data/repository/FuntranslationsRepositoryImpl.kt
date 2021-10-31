package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSource
import com.fgdc.pokespearesdk.domain.repository.FuntranslationsRepository
import javax.inject.Inject

class FuntranslationsRepositoryImpl
@Inject constructor(private val funtranslationsRemoteSource: FuntranslationsRemoteSource) :
    FuntranslationsRepository {
    override suspend fun getTextTranslated(text: String) =
        funtranslationsRemoteSource.getTextTranslated(text)
}
