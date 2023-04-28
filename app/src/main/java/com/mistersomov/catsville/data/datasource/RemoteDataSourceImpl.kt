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