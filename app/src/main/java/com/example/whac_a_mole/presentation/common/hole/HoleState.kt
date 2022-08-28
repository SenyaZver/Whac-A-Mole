package com.example.whac_a_mole.presentation.common.hole

data class HoleState (
    var id: Int = 0,
    val moleAppears : Boolean = false,
    val moleClicked: Boolean = false
        )