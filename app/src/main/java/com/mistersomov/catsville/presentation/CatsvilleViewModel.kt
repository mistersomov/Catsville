package com.mistersomov.catsville.presentation

import androidx.lifecycle.viewModelScope
import com.mistersomov.catsville.base.BaseViewModel
import com.mistersomov.catsville.domain.use_case.FetchDataUseCase
import com.mistersomov.catsville.presentation.model.CatsvilleAction
import com.mistersomov.catsville.presentation.model.CatsvilleEvent
import com.mistersomov.catsville.presentation.model.CatsvilleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsvilleViewModel @Inject constructor(
    private val fetchDataUseCase: FetchDataUseCase,
) : BaseViewModel<CatsvilleViewState, CatsvilleAction, CatsvilleEvent>(CatsvilleViewState()) {

    override fun obtainEvent(event: CatsvilleEvent) {
        when (event) {
            is CatsvilleEvent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchDataUseCase()
                .cancellable()
                .collect { catList ->
                    viewState = when {
                        catList.isNotEmpty() -> viewState.copy(cats = catList, isLoading = false)
                        else -> viewState.copy(cats = emptyList(), isLoading = false)
                    }
                }
        }
    }
}