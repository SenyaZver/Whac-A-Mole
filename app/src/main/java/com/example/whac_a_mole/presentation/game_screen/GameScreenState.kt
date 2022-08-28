package com.example.whac_a_mole.presentation.game_screen

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants.amountOfHoles
import com.example.whac_a_mole.common.Constants.gameDuration
import com.example.whac_a_mole.presentation.game_screen.hole.HoleState

data class GameScreenState (
    val gameTimer: CountDownTimer? = null,
    val moleTimer: CountDownTimer? = null,
    var timeLeft: Long = gameDuration,
    var score: Int = 0,
    var chosenHoleIndex: Int = 0,
    val holeStates: MutableList<HoleState> = createHoleStates()
)


private fun createHoleStates(): MutableList<HoleState> {
    val list: MutableList<HoleState> = mutableListOf()

    for (i in 0 until amountOfHoles) {
        list.add(HoleState(id = i))
    }
    return list
}