package com.fgdc.pokespearesdk.data.source.funtranslations

import com.fgdc.pokespearesdk.utils.helpers.NetworkHandler
import com.fgdc.pokespearesdk.data.mappers.toShakespareTranslation
import com.fgdc.pokespearesdk.data.source.funtranslations.api.FuntranslationsApi
import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.exception.ErrorHandler
import com.fgdc.pokespearesdk.utils.functional.State

class FuntranslationsRemoteSourceImpl(
    private val funtranslationsApi: FuntranslationsApi,
    private val networkHandler: NetworkHandler
) : FuntranslationsRemoteSource {
    override suspend fun getTextTranslated(text: String): State<ShakespeareanTranslation> {
        if (!networkHandler.isInternetAvailable()) {
            return State.ErrorNoConnection(Throwable(ErrorHandler.NETWORK_ERROR_MESSAGE))
        }
        return try {
            funtranslationsApi.getTextTranslated(text).run {
                if (isSuccessful && body() != null) {
                    State.Success(body()!!.toShakespareTranslation())
                } else {
                    State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR))
        }
    }
}
