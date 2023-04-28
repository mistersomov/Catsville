package com.mistersomov.catsville.domain.use_case

import com.mistersomov.catsville.di.qualifier.DefaultDispatcher
import com.mistersomov.catsville.domain.model.Cat
import com.mistersomov.catsville.domain.repository.CatsvilleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val repository: CatsvilleRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<Cat>> = repository.fetchData().flowOn(defaultDispatcher)
}