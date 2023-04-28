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

package com.mistersomov.catsville.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mistersomov.catsville.data.network.api.CatsvilleApiService
import com.mistersomov.catsville.data.network.model.CatDto
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CatsvilleRemoteMediator @Inject constructor(
    private val service: CatsvilleApiService,
) : RemoteMediator<Int, CatDto>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CatDto>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.id
                }
            }
            val response = service.getCats()
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (th: Throwable) {
            MediatorResult.Error(th)
        }
    }
}