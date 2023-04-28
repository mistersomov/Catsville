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

package com.mistersomov.catsville.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.mistersomov.catsville.domain.model.Cat
import com.mistersomov.catsville.presentation.screen.ErrorScreen
import com.mistersomov.catsville.presentation.screen.LoadingScreen
import com.mistersomov.catsville.presentation.screen.PageLoading

@Composable
fun CatsvilleScreen(viewModel: CatsvilleViewModel = hiltViewModel()) {
    val cats: LazyPagingItems<Cat> = viewModel.fetchData().collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        color = MaterialTheme.colors.surface,
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = cats, key = { it.id }) {
                CatItem(cat = it)
            }

            when (cats.loadState.refresh) {
                is LoadState.Error -> Unit
                is LoadState.Loading -> {
                    item {
                        LoadingScreen(modifier = Modifier.fillParentMaxSize())
                    }
                }
                else -> Unit
            }
            when (cats.loadState.append) {
                is LoadState.Error -> {
                    item {
                        ErrorScreen(modifier = Modifier.fillMaxWidth(1f))
                    }
                }
                is LoadState.Loading -> {
                    item {
                        PageLoading(modifier = Modifier.fillMaxWidth(1f))
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun CatItem(cat: Cat?) {
    val imageModel = remember { cat?.url }
    AsyncImage(
        model = imageModel,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .size(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {

                }),
        contentDescription = null,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}