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

package com.mistersomov.catsville.domain.use_case

import androidx.paging.PagingData
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
    operator fun invoke(): Flow<PagingData<Cat>> = repository.fetchData().flowOn(defaultDispatcher)
}