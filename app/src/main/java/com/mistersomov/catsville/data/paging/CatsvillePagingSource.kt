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

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mistersomov.catsville.data.mapper.toCat
import com.mistersomov.catsville.data.network.api.CatsvilleApiService
import com.mistersomov.catsville.domain.model.Cat
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatsvillePagingSource @Inject constructor(
    private val service: CatsvilleApiService,
) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val pageNumber: Int = params.key ?: 0
            val response = service.getCats(page = pageNumber).map { it.toCat() }
            val prevKey: Int? = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey: Int? = if (response.isNotEmpty()) pageNumber + 1 else null

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}