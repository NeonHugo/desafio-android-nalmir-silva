package com.nm.desafio_android_nalmir_hugo.di

import com.nm.data.di.MapperModules.charactersResponseToCharacters
import com.nm.data.di.MapperModules.comicsResponseToComics
import com.nm.data.repository.*
import com.nm.data.services.MarvelService
import com.nm.desafio_android_nalmir_hugo.ui.comicBookDetail.ComicBookDetailViewModel
import com.nm.desafio_android_nalmir_hugo.ui.characterDetail.CharactersDetailViewModel
import com.nm.desafio_android_nalmir_hugo.ui.charactersList.CharactersListViewModel
import com.nm.domain.repository.ComicsRepository
import com.nm.domain.repository.MCharacterRepository
import com.nm.domain.usecase.ComicUseCase
import com.nm.domain.usecase.ComicUseCaseImpl
import com.nm.domain.usecase.MCharacterUseCase
import com.nm.domain.usecase.MCharacterUseCaseImpl
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.NetworkImpl
import com.nm.infrastructure.net.RetrofitBuild.makeService
import com.nm.infrastructure.util.error.ResourcesStringError
import com.nm.infrastructure.util.error.ResourcesStringErrorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModules {

    const val marvelService = "MarvelService"

    val serviceModules = module {
        single(named(marvelService)) { makeService<MarvelService>(get()) }

        viewModel { CharactersListViewModel(get(), get()) }
        viewModel { CharactersDetailViewModel(get()) }
        viewModel { ComicBookDetailViewModel(get(), get()) }
    }

    val dataModules = module {
        single<Network> { NetworkImpl(get()) }
        single<ResourcesStringError> { ResourcesStringErrorImpl(get()) }
    }

    val dataSourceModules = module {
        single<CharactersRemoteDataSource> {
            CharactersRemoteDataSourceImpl(
                get(named(marvelService)),
                get(named(charactersResponseToCharacters))
            )
        }

        single<ComicsRemoteDataSource> {
            ComicsRemoteDataSourceImpl(
                get(named(marvelService)),
                get(named(comicsResponseToComics))
            )
        }

    }

    val repositoryModules = module {
        single<MCharacterRepository> { MCharacterRepositoryImpl(get()) }
        single<ComicsRepository> { ComicsRepositoryImpl(get()) }
    }

    val useCaseModules = module {
        single<MCharacterUseCase> { MCharacterUseCaseImpl(get()) }
        single<ComicUseCase> { ComicUseCaseImpl(get()) }
    }

}
