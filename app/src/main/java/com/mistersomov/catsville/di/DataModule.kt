package com.mistersomov.catsville.di

import com.mistersomov.catsville.data.datasource.RemoteDataSource
import com.mistersomov.catsville.data.datasource.RemoteDataSourceImpl
import com.mistersomov.catsville.data.repository.CatsvilleRepositoryImpl
import com.mistersomov.catsville.domain.repository.CatsvilleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCatsvilleRepository(impl: CatsvilleRepositoryImpl): CatsvilleRepository
}