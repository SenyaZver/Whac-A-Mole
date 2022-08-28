package com.example.whac_a_mole.model

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants
import com.example.whac_a_mole.presentation.common.hole.HoleState

data class GameState(
    val gameTimer: CountDownTimer? = null,
    val moleTimer: CountDownTimer? = null,
    val timeLeft: Long = Constants.gameDuration,
    val score: Int = 0,
    val chosenHoleIndex: Int = 0,
    val holeStates: MutableList<HoleState> = createHoleStates()
)


private fun createHoleStates(): MutableList<HoleState> {
    val list: MutableList<HoleState> = mutableListOf()

    for (i in 0 until Constants.amountOfHoles) {
        list.add(HoleState(id = i))
    }
    return list
}