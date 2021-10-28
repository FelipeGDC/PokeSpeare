package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSource
import com.fgdc.pokespearesdk.domain.repository.FuntranslationsRepository
import com.fgdc.pokespearesdk.utils.exception.ErrorHandler
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FuntranslationsRepositoryImpl
@Inject constructor(private val funtranslationsRemoteSource: FuntranslationsRemoteSource) :
    FuntranslationsRepository {
    override fun getTextTranslated(text: String) = flow {
        emit(funtranslationsRemoteSource.getTextTranslated(text))
    }.catch {
        it.printStackTrace()
        emit(State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR)))
    }
}
