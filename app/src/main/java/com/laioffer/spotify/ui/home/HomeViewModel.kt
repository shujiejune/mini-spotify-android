package com.laioffer.spotify.ui.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    // state
    // list, mutableList
    // stateflow: Stateflow, mutableStateFlow

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState(feed = listOf(), isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // handle event
    fun fetchHomeScreen() {


        viewModelScope.launch {

            val sections = homeRepository.getHomeSections()
            _uiState.value = HomeUiState(feed = sections, isLoading = false)

            Log.d("HomeViewModel", _uiState.value.toString())
        }
    }
}

data class HomeUiState (
    val isLoading: Boolean,
    val feed: List<Section>
)