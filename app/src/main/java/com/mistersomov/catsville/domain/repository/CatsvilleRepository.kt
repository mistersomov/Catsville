package com.mistersomov.catsville.domain.repository

import com.mistersomov.catsville.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatsvilleRepository {
    fun fetchData(): Flow<List<Cat>>
}