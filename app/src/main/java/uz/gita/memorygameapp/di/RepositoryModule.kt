package uz.gita.memorygameapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygameapp.data.repository.AppRepository
import uz.gita.memorygameapp.data.repository.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryImpl: AppRepositoryImpl) : AppRepository
}