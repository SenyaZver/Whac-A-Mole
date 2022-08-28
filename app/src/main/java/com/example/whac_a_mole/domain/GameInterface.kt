package com.example.whac_a_mole.domain

interface GameInterface {

    fun start()

    fun stop()

    fun changeChosenHole()

    fun holeClicked(index: Int)

    fun saveScore()


}