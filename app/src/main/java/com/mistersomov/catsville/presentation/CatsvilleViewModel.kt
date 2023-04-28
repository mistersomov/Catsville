package com.mistersomov.catsville.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistersomov.catsville.domain.use_case.FetchDataUseCase
import com.mistersomov.catsville.presentation.model.CatsvilleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsvilleViewModel @Inject constructor(
    private val fetchDataUseCase: FetchDataUseCase,
) : ViewModel() {

    fun obtainEvent(event: CatsvilleEvent) {
        when (event) {
            is CatsvilleEvent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchDataUseCase.invoke().collect { catList ->
                Log.d("TEST", catList.toString())
            }
        }
    }
}