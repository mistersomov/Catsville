package com.mistersomov.catsville.data.network.api

import com.mistersomov.catsville.data.network.model.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsvilleApiService {

    @GET(ENDPOINT)
    suspend fun getCats(
        @Query(QUERY_PARAM_LIMIT) limit: Int = QUERY_PARAM_LIMIT_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = QUERY_PARAM_PAGE_DEFAULT,
    ): List<CatDto>

    companion object {
        //Endpoints
        private const val ENDPOINT = "/v1/images/search"

        //Query params
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_LIMIT_DEFAULT = 100
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PAGE_DEFAULT = 0
    }
}