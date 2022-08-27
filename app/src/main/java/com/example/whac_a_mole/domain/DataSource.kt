package com.example.whac_a_mole.domain

interface DataSource {

    suspend fun getHighscore(): Int

    suspend fun setHighscore()

    suspend fun setCurrentScore(score: Int)

    suspend fun getCurrentScore(): Int
}