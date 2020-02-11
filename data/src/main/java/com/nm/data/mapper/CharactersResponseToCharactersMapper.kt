package com.nm.data.mapper

import com.nm.data.model.CharactersResponse
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.Mapper

class CharactersResponseToCharactersMapper : Mapper<CharactersResponse, List<MCharacter>>() {
    override fun transform(charactersResponse: CharactersResponse?): List<MCharacter> {
        val characters = mutableListOf<MCharacter>()

        charactersResponse?.data?.results.let {
            it?.forEach { item ->
                characters.add(
                    MCharacter(
                        id = item.id,
                        name = item.name,
                        description = item.description,
                        thumbnail = "${item.thumbnail.path}.${item.thumbnail.extension}".replace("http://", "https://")
                    )
                )
            }
        }

        return characters
    }

}