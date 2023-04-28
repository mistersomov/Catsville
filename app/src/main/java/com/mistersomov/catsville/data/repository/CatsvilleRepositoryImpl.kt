package com.mistersomov.catsville.data.repository

import com.mistersomov.catsville.data.datasource.RemoteDataSource
import com.mistersomov.catsville.data.mapper.toCat
import com.mistersomov.catsville.domain.model.Cat
import com.mistersomov.catsville.domain.repository.CatsvilleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatsvilleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : CatsvilleRepository {
    override fun fetchData(): Flow<List<Cat>> = remoteDataSource.getCats().map { list ->
        list.map { it.toCat() }
    }
}