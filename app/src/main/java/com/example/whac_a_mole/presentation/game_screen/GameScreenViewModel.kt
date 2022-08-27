package com.example.whac_a_mole.presentation.game_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whac_a_mole.data.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val game: Game
): ViewModel() {
    private val _uiState = MutableStateFlow(GameScreenState())
    val uiState: StateFlow<GameScreenState> = _uiState


    init {
        viewModelScope.launch {
            game.start()

            game.gameState.collect {gameState ->
                _uiState.value = gameState
            }
        }

    }


    fun holeClicked(index: Int) {
        game.holeClicked(index)
    }


    fun saveScore(score: Int) {
        game.saveScore(score)
    }




}