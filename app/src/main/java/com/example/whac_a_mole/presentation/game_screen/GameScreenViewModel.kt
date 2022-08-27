package com.example.whac_a_mole.presentation.game_screen

import android.os.CountDownTimer
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.common.Constants.amountOfHoles
import com.example.whac_a_mole.common.Constants.gameDuration
import com.example.whac_a_mole.domain.DataSource
import com.example.whac_a_mole.presentation.game_screen.hole.HoleState
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
            _uiState.value = GameScreenState(
                gameTimer = getGameTimer(gameDuration * 1000, 1000),
                moleTimer = getMoleTimer(gameDuration * 1000, 500)
            )
            delay(1000)
            _uiState.value.gameTimer?.start()
            _uiState.value.moleTimer?.start()
        }
    }


    fun holeClicked(index: Int) {
        viewModelScope.launch(Dispatchers.Unconfined) {
            if (_uiState.value.holeStates[index].moleAppears == true) {
                val newGameScreenState = GameScreenState(
                    gameTimer = _uiState.value.gameTimer,
                    timeLeft = _uiState.value.timeLeft,
                    score = _uiState.value.score + 1,
                    chosenHoleIndex = _uiState.value.chosenHoleIndex,
                    holeStates = _uiState.value.holeStates
                )
                _uiState.value = newGameScreenState
            }
        }
    }

    fun changeChosenHole() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            //generate new chosen hole
            val newHoleIndex = (0..amountOfHoles-1).random()
            val newHoleState = HoleState(id = newHoleIndex, moleAppears = true)

            //get current chosen hole to change it back
            val currentChosenHoleIndex = _uiState.value.chosenHoleIndex

            //update hole states
            val holeStates = _uiState.value.holeStates.toMutableList()
            holeStates[currentChosenHoleIndex] = HoleState(id = currentChosenHoleIndex, moleAppears = false)
            holeStates[newHoleIndex] = newHoleState

            //update game state
            val newGameScreenState = GameScreenState(
                gameTimer = _uiState.value.gameTimer,
                timeLeft = _uiState.value.timeLeft,
                score = _uiState.value.score,
                chosenHoleIndex = newHoleIndex,
                holeStates = holeStates
            )
            _uiState.value = newGameScreenState
        }
    }

    fun getScore(): Int {
        return _uiState.value.score
    }

    private fun getGameTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {



            override fun onTick(p0: Long) {
                viewModelScope.launch(Dispatchers.Unconfined) {

                    val newGameScreenState = GameScreenState(
                        gameTimer = _uiState.value.gameTimer,
                        timeLeft = _uiState.value.timeLeft - 1,
                        score = _uiState.value.score,
                        chosenHoleIndex = _uiState.value.chosenHoleIndex,
                        holeStates = _uiState.value.holeStates)
                    _uiState.value = newGameScreenState
                }

            }
            override fun onFinish() {
            }
        }

    private fun getMoleTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {
            override fun onTick(p0: Long) {
                changeChosenHole()
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