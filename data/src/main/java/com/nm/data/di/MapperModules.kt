package com.nm.data.di

import com.nm.data.mapper.CharactersResponseToCharactersMapper
import com.nm.data.mapper.ComicsResponseToComicMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {

    const val charactersResponseToCharacters = "CharactersResponseToCharactersMapper"
    const val comicsResponseToComics = "ComicsResponseToComicMapper"

    val mapperModules = module {
        single(named(charactersResponseToCharacters)) { CharactersResponseToCharactersMapper() }
        single(named(comicsResponseToComics)) { ComicsResponseToComicMapper() }
    }

}