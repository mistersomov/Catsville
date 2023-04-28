package com.mistersomov.catsville.presentation.model

import com.mistersomov.catsville.domain.model.Cat

data class CatsvilleViewState(
    val cats: List<Cat> = emptyList(),
    val isLoading: Boolean = true,
)
