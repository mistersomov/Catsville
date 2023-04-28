package com.mistersomov.catsville.presentation

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mistersomov.catsville.domain.model.Cat
import com.mistersomov.catsville.presentation.model.CatsvilleViewState

@Composable
fun CatsvilleDisplay(
    viewState: CatsvilleViewState,
) {
    val cats = remember { viewState.cats }
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = true,
    ) {
        items(items = cats) { cat ->
            CatItem(cat = cat)
        }
    }
}

@Composable
fun CatItem(cat: Cat) {
    val imageModel = remember { cat.url }
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