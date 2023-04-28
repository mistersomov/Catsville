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

package com.mistersomov.catsville.data.datasource

import com.mistersomov.catsville.data.network.api.CatsvilleApiService
import com.mistersomov.catsville.data.network.model.CatDto
import com.mistersomov.catsville.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: CatsvilleApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : RemoteDataSource {
    override fun getCats(): Flow<List<CatDto>> = flow {
        val response = service.getCats()

        emit(response)
    }.flowOn(ioDispatcher)
}