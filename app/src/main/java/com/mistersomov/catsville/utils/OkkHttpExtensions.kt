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

package com.mistersomov.catsville.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun <T : Any> OkHttpClient.Builder.addInterceptor(
    interceptors: List<Interceptor>,
    interceptorType: Class<T>
): OkHttpClient.Builder {
    interceptors
        .filter { interceptorType.isInstance(it) }
        .forEach(this::addInterceptor)

    return this
}