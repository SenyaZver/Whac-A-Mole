package com.example.whac_a_mole.model

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants
import com.example.whac_a_mole.domain.DataSource
import com.example.whac_a_mole.domain.GameInterface
import com.example.whac_a_mole.presentation.game_screen.GameScreenState
import com.example.whac_a_mole.presentation.common.hole.HoleState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class Game @Inject constructor(
    private val dataSource: DataSource
): GameInterface {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState


    override fun start() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            _gameState.value = GameState(
                gameTimer = getGameTimer(Constants.gameDuration * 1000, 1000),
                moleTimer = getMoleTimer(Constants.gameDuration * 1000, 500)
            )
            delay(1000)
            _gameState.value.gameTimer?.start()
            _gameState.value.moleTimer?.start()

        }
    }

    override fun stop() {
        _gameState.value.gameTimer?.cancel()
        _gameState.value.moleTimer?.cancel()

        _gameState.value = GameState()
    }


    override fun changeChosenHole() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            //generate new chosen hole
            val newHoleIndex = (0..Constants.amountOfHoles -1).random()
            val newHoleState = HoleState(id = newHoleIndex, moleAppears = true)

            //get current chosen hole to change it back
            val currentChosenHoleIndex = _gameState.value.chosenHoleIndex

            //update hole states
            _gameState.value.holeStates[currentChosenHoleIndex] = HoleState(id = currentChosenHoleIndex, moleAppears = false)
            _gameState.value.holeStates[newHoleIndex] = newHoleState

            //update game state
            val newGameScreenState = changeState(_gameState.value, chosenHoleIndex = newHoleIndex)
            _gameState.value = newGameScreenState
        }
    }

    override fun holeClicked(index: Int) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            val clickedHoleState = _gameState.value.holeStates[index]

            if (clickedHoleState.moleAppears && clickedHoleState.moleClicked == false) {
                _gameState.value.holeStates[index] = HoleState(id = index, moleAppears = true, moleClicked = true)

                val newGameScreenState = changeState(_gameState.value, score = _gameState.value.score + 1)
                _gameState.value = newGameScreenState

            }
        }
    }

    override fun saveScore() {
        CoroutineScope(Dispatchers.Unconfined).launch {
            dataSource.setCurrentScore(gameState.value.score)
        }
    }


    private fun getGameTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {

            override fun onTick(p0: Long) {
                CoroutineScope(Dispatchers.Unconfined).launch {
                    val newGameScreenState = changeState(_gameState.value, timeLeft = _gameState.value.timeLeft - 1)
                    _gameState.value = newGameScreenState
                }
            }
            override fun onFinish() {
                this.cancel()
            }
        }

    private fun getMoleTimer(timeMillis: Long, interval: Long) =
        object : CountDownTimer(timeMillis, interval) {

            override fun onTick(p0: Long) {
                changeChosenHole()
            }
            override fun onFinish() {
                this.cancel()
            }
        }

    private fun changeState(
        state: GameState,
        gameTimer: CountDownTimer? = state.gameTimer,
        moleTimer: CountDownTimer? = state.moleTimer,
        timeLeft: Long = state.timeLeft,
        score: Int = state.score,
        chosenHoleIndex: Int = state.chosenHoleIndex,
        holeStates: MutableList<HoleState> = state.holeStates
    ): GameState {
        return GameState(
            gameTimer = gameTimer,
            moleTimer = moleTimer,
            timeLeft = timeLeft,
            score = score,
            chosenHoleIndex = chosenHoleIndex,
            holeStates = holeStates
        )
    }



}