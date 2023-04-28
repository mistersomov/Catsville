package com.mistersomov.catsville.domain.model

data class CatsvilleViewState(
    val cats: List<Cat>,
    val isLoading: Boolean = true,
)
