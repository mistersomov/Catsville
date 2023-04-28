package com.mistersomov.catsville.di

import com.mistersomov.catsville.data.network.interceptor.CatsvilleInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterceptorModule {
    @Singleton
    @Binds
    @IntoSet
    abstract fun bindCoinProtocolInterceptor(impl: CatsvilleInterceptor): Interceptor

    @Multibinds
    abstract fun interceptors(): Set<Interceptor>
}