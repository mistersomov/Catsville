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

package com.mistersomov.catsville.data.network.api

import com.mistersomov.catsville.data.network.model.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsvilleApiService {
    companion object {
        //Endpoints
        private const val ENDPOINT = "/v1/images/search"

        //Query params
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_LIMIT_DEFAULT = 10
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PAGE_DEFAULT = 0
    }

    @GET(ENDPOINT)
    suspend fun getCats(
        @Query(QUERY_PARAM_LIMIT) limit: Int = QUERY_PARAM_LIMIT_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = QUERY_PARAM_PAGE_DEFAULT,
    ): List<CatDto>
}