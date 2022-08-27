package com.example.whac_a_mole.presentation.start_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.domain.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartScreenViewModel @Inject constructor(
    dataSource: DataSource
): ViewModel() {

    private val _state = MutableStateFlow(StartScreenState())
    val state: StateFlow<StartScreenState> = _state


    init {
        viewModelScope.launch(Dispatchers.Main) {
            val highscore = dataSource.getHighscore()
            _state.value = StartScreenState(highscore = highscore)
        }
    }


}