package com.example.whac_a_mole.presentation.result_screen

data class ResultScreenState (
    val score: Int = 0,
    val highScore: Int = 0,
    val isHighScore: Boolean = false
        )