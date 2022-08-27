package com.example.whac_a_mole.presentation.result_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.common.Constants
import com.example.whac_a_mole.domain.DataSource
import com.example.whac_a_mole.presentation.game_screen.GameScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val dataSource: DataSource
): ViewModel() {
    private val _uiState = MutableStateFlow(ResultScreenState())
    val uiState: StateFlow<ResultScreenState> = _uiState


    init {
        viewModelScope.launch(Dispatchers.Unconfined) {
            val score = dataSource.getCurrentScore()
            var highScore = dataSource.getHighscore()
            var isHighScore = false

            if (score > highScore) {
                dataSource.setHighscore()
                highScore = score
                isHighScore = true
            }

            _uiState.value = ResultScreenState(score, highScore, isHighScore)

        }
    }




}