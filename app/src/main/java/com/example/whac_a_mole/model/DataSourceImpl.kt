package com.example.whac_a_mole.model

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.example.whac_a_mole.common.Constants.highscoreDataStoreName
import com.example.whac_a_mole.common.Constants.highscoreParam
import com.example.whac_a_mole.domain.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataSourceImpl(private val context: Context): DataSource {

    var currentScore: Int = 0
    var highScore: Int = 0


    val dataStore: DataStore<Preferences> = context.createDataStore(
        name = highscoreDataStoreName
    )
    val HIGHSCORE_KEY = preferencesKey<Int>(highscoreParam)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadScore()
        }

    }

    override suspend fun getHighscore(): Int {
        return highScore
    }

    override suspend fun setHighscore() {
        dataStore.edit { settings ->
            settings[HIGHSCORE_KEY] = currentScore
        }
        highScore = currentScore
    }

    override suspend fun setCurrentScore(score: Int) {
        currentScore = score
    }

    override suspend fun getCurrentScore(): Int {
        return currentScore
    }

    private suspend fun loadScore() {
        val highscoreFlow: Flow<Int> = dataStore.data
            .map { currentPreferences ->
                currentPreferences[HIGHSCORE_KEY] ?: 0
            }
        highscoreFlow.collect{
            highScore = it
        }
    }



}