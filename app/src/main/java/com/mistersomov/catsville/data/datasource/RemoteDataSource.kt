package com.mistersomov.catsville.data.datasource

import com.mistersomov.catsville.data.network.model.CatDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getCats(): Flow<List<CatDto>>
}