/*
 * Copyright (C) 2023 Ivan Somov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mistersomov.catsville.di

import com.mistersomov.catsville.BuildConfig
import com.mistersomov.catsville.data.network.api.CatsvilleApiService
import com.mistersomov.catsville.data.network.interceptor.CatsvilleInterceptor
import com.mistersomov.catsville.utils.addInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideOkkHttp(interceptors: MutableSet<Interceptor>): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val interceptorList: ArrayList<Interceptor> = ArrayList(interceptors)

        return OkHttpClient.Builder()
            .addInterceptor(interceptorList, CatsvilleInterceptor::class.java)
            .addNetworkInterceptor(logger)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BuildConfig.baseUrl)
        .build()

    @Singleton
    @Provides
    fun provideCatsvilleApiService(retrofit: Retrofit): CatsvilleApiService =
        retrofit.create(CatsvilleApiService::class.java)
}