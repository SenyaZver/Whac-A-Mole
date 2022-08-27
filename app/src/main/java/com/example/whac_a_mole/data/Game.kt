package com.example.whac_a_mole.data

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants
import com.example.whac_a_mole.domain.DataSource
import com.example.whac_a_mole.presentation.game_screen.GameScreenState
import com.example.whac_a_mole.presentation.game_screen.hole.HoleState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Game @Inject constructor(
    private val dataSource: DataSource
) {
    private val _gameState = MutableStateFlow(GameScreenState())
    val gameState: StateFlow<GameScreenState> = _gameState



    fun start() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            _gameState.value = GameScreenState(
                gameTimer = getGameTimer(Constants.gameDuration * 1000, 1000),
                moleTimer = getMoleTimer(Constants.gameDuration * 1000, 500)
            )
            delay(1000)
            _gameState.value.gameTimer?.start()
            _gameState.value.moleTimer?.start()
        }
    }


    fun changeChosenHole() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            //generate new chosen hole
            val newHoleIndex = (0..Constants.amountOfHoles -1).random()
            val newHoleState = HoleState(id = newHoleIndex, moleAppears = true)

            //get current chosen hole to change it back
            val currentChosenHoleIndex = _gameState.value.chosenHoleIndex

            //update hole states
            val holeStates = _gameState.value.holeStates.toMutableList()
            holeStates[currentChosenHoleIndex] = HoleState(id = currentChosenHoleIndex, moleAppears = false)
            holeStates[newHoleIndex] = newHoleState

            //update game state
            val newGameScreenState = GameScreenState(
                gameTimer = _gameState.value.gameTimer,
                timeLeft = _gameState.value.timeLeft,
                score = _gameState.value.score,
                chosenHoleIndex = newHoleIndex,
                holeStates = holeStates
            )
            _gameState.value = newGameScreenState
        }
    }

    fun holeClicked(index: Int) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            if (_gameState.value.holeStates[index].moleAppears == true) {
                val newGameScreenState = GameScreenState(
                    gameTimer = _gameState.value.gameTimer,
                    timeLeft = _gameState.value.timeLeft,
                    score = _gameState.value.score + 1,
                    chosenHoleIndex = _gameState.value.chosenHoleIndex,
                    holeStates = _gameState.value.holeStates
                )
                _gameState.value = newGameScreenState
            }
        }
    }


    fun saveScore(score: Int) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            dataSource.setCurrentScore(score)
        }
    }


    private fun getGameTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {



            override fun onTick(p0: Long) {
                CoroutineScope(Dispatchers.Unconfined).launch {

                    val newGameScreenState = GameScreenState(
                        gameTimer = _gameState.value.gameTimer,
                        timeLeft = _gameState.value.timeLeft - 1,
                        score = _gameState.value.score,
                        chosenHoleIndex = _gameState.value.chosenHoleIndex,
                        holeStates = _gameState.value.holeStates)
                    _gameState.value = newGameScreenState
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



}