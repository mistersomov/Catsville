package com.mistersomov.catsville.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.R
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mistersomov.catsville.presentation.model.CatsvilleEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatsvilleScreen(viewModel: CatsvilleViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = { },
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = MaterialTheme.colors.primary,
        peekHeight = 90.dp,
        backLayerContent = {

        },
        frontLayerElevation = 20.dp,
        frontLayerBackgroundColor = MaterialTheme.colors.surface,
        frontLayerContent = {
//            when {
//                viewState.isLoading && viewState.coinList.isEmpty() -> CoinViewLoading()
//                !viewState.isLoading && viewState.coinList.isNotEmpty() -> CoinViewDisplay(
//                    viewState = viewState,
//                    navController = navController,
//                    onCoinClicked = remember {
//                        {
//                            scope.launch { scaffoldState.reveal() }
//                            viewModel.obtainEvent(CoinEvent.CoinClick(it))
//                        }
//                    }
//                )
//                !viewState.isLoading -> Unit
//                else -> throw NotImplementedError(
//                    stringResource(id = R.string.crypto_implementation_state_error)
//                )
            }
        )
    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(CatsvilleEvent.FetchData)
    })
}
