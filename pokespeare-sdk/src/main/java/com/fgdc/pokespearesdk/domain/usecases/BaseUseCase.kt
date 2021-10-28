package com.fgdc.pokespearesdk.domain.usecases

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<out Type, in Params> where Type : Any? {
    abstract suspend fun run(params: Params? = null): Flow<Type>

    @JvmOverloads
    suspend operator fun invoke(
        params: Params? = null
    ): Flow<Type> {
        return run(params)
    }

    class None
}
