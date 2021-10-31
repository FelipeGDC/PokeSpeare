package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPokemonSprite @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<State<PokemonSprite>, GetPokemonSprite.Params>() {

    override suspend fun run(params: Params?): State<PokemonSprite> {
        return pokemonRepository.getPokemonSprite(name = params!!.name)
    }

    class Params(var name: String)
}
