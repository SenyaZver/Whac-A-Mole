package com.example.whac_a_mole.presentation.game_screen

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants.amountOfHoles
import com.example.whac_a_mole.common.Constants.gameDuration
import com.example.whac_a_mole.presentation.game_screen.hole.HoleState

data class GameScreenState (
    val gameTimer: CountDownTimer? = null,
    val moleTimer: CountDownTimer? = null,
    val timeLeft: Long = gameDuration,
    val score: Int = 0,
    val chosenHoleIndex: Int = 0,
    val holeStates: MutableList<HoleState> = createHoleStates()
)


private fun createHoleStates(): MutableList<HoleState> {
    val list: MutableList<HoleState> = mutableListOf()

    for (i in 0 until amountOfHoles) {
        list.add(HoleState(id = i))
    }
    return list
}