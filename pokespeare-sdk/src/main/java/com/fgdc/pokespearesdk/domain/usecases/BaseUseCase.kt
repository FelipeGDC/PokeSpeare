package com.fgdc.pokespearesdk.domain.usecases

abstract class BaseUseCase<out Type, in Params> where Type : Any? {
    abstract suspend fun run(params: Params? = null): Type

    @JvmOverloads
    suspend operator fun invoke(
        params: Params? = null
    ): Type {
        return run(params)
    }

    class None
}
