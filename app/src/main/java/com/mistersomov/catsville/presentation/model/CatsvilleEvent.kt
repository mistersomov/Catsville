package com.mistersomov.catsville.presentation.model

sealed class CatsvilleEvent {
    object FetchData : CatsvilleEvent()
}
