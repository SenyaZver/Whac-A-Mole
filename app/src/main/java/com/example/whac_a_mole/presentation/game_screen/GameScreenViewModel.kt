package com.example.whac_a_mole.presentation.game_screen

import android.os.CountDownTimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.common.Constants.gameDuration
import com.example.whac_a_mole.domain.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val dataSource: DataSource
): ViewModel() {
    private val _uiState = MutableStateFlow(GameScreenState())
    val uiState: StateFlow<GameScreenState> = _uiState


    init {
        viewModelScope.launch(Dispatchers.Unconfined) {
            _uiState.value = GameScreenState(timer = getTimer(gameDuration * 1000, 1000))
            delay(1000)
            _uiState.value.timer?.start()
        }
    }


    fun increaseScore() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            val newGameScreenState = GameScreenState(
                timer = _uiState.value.timer,
                timeLeft = _uiState.value.timeLeft,
                score = _uiState.value.score + 1,
                millsLeftToShowMole = _uiState.value.millsLeftToShowMole
            )
            _uiState.value = newGameScreenState
        }
    }

    fun changeChosenHole() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            val newGameScreenState = GameScreenState(
                timer = _uiState.value.timer,
                timeLeft = _uiState.value.timeLeft,
                score = _uiState.value.score,
                millsLeftToShowMole = 500,
                chosenHoleIndex = (0..8).random()
            )
            _uiState.value = newGameScreenState
        }
    }

    fun getScore(): Int {
        return _uiState.value.score
    }

    private fun getTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {
            override fun onTick(p0: Long) {

                viewModelScope.launch(Dispatchers.Unconfined) {
                    val newGameScreenState = GameScreenState(
                        timer = _uiState.value.timer,
                        timeLeft = _uiState.value.timeLeft - 1,
                        score = _uiState.value.score,
                        millsLeftToShowMole = _uiState.value.millsLeftToShowMole
                    )
                    _uiState.value = newGameScreenState

                }

            }
            override fun onFinish() {
            }
        }

    fun saveScore(score: Int) {
        viewModelScope.launch(Dispatchers.Unconfined) {
            dataSource.setCurrentScore(score)
        }
    }



}