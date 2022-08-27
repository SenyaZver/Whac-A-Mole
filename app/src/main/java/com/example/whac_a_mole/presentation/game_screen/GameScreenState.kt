package com.example.whac_a_mole.presentation.game_screen

import android.os.CountDownTimer
import com.example.whac_a_mole.common.Constants.gameDuration

data class GameScreenState (
    val timer: CountDownTimer? = null,
    val timeLeft: Long = gameDuration,
    val score: Int = 0,
    val millsLeftToShowMole: Long = 500,
    val chosenHoleIndex: Int = 0
)