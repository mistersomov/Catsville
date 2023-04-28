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

package com.mistersomov.catsville.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mistersomov.catsville.data.network.api.CatsvilleApiService
import com.mistersomov.catsville.data.paging.CatsvillePagingSource
import com.mistersomov.catsville.di.qualifier.DefaultDispatcher
import com.mistersomov.catsville.domain.model.Cat
import com.mistersomov.catsville.domain.repository.CatsvilleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatsvilleRepositoryImpl @Inject constructor(
    private val service: CatsvilleApiService,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : CatsvilleRepository {
    companion object {
        private const val PAGE_SIZE = 10
    }
    override fun fetchData(): Flow<PagingData<Cat>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { CatsvillePagingSource(service) }
    )
        .flow
        .flowOn(defaultDispatcher)
}