package com.example.whac_a_mole.presentation.game_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val game: Game
): ViewModel() {

    val uiState = MutableStateFlow(GameScreenState())

    init {
        viewModelScope.launch {
            game.start()

            game.gameState.collect {gameState ->
                uiState.value = gameState
            }
        }
    }


    fun holeClicked(index: Int) {
        game.holeClicked(index)
    }


    fun gameEnded() {
        game.saveScore()
        game.stop()
    }

    fun gameCancelled() {
        game.stop()
    }




}