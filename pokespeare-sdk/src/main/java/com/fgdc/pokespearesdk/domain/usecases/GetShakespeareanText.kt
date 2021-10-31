package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.domain.repository.FuntranslationsRepository
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject

class GetShakespeareanText @Inject constructor(
    private val funtranslationsRepository: FuntranslationsRepository
) : BaseUseCase<State<ShakespeareanTranslation>, GetShakespeareanText.Params>() {

    override suspend fun run(params: Params?): State<ShakespeareanTranslation> {
        return funtranslationsRepository.getTextTranslated(text = params!!.text)
    }

    class Params(var text: String)
}
