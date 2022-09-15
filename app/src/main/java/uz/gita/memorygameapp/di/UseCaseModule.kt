package uz.gita.memorygameapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.memorygameapp.domain.useCase.AllCardDataUseCase
import uz.gita.memorygameapp.domain.useCase.GameUseCase
import uz.gita.memorygameapp.domain.useCase.TwoPlayerUseCase
import uz.gita.memorygameapp.domain.useCase.impl.AllCardDataUseCaseImpl
import uz.gita.memorygameapp.domain.useCase.impl.GameUseCaseImpl
import uz.gita.memorygameapp.domain.useCase.impl.TwoPlayerUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindAllDataUseCase(useCase : AllCardDataUseCaseImpl) : AllCardDataUseCase

    @Binds
    fun bindgameUseCase(usecase : GameUseCaseImpl) : GameUseCase

    @Binds
    fun bindTwoGameUseCase(usecase : TwoPlayerUseCaseImpl) : TwoPlayerUseCase
}