package com.example.whac_a_mole.di

import android.content.Context
import com.example.whac_a_mole.model.DataSourceImpl
import com.example.whac_a_mole.model.Game
import com.example.whac_a_mole.domain.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun getDataSource(@ApplicationContext appContext: Context): DataSource {
        return DataSourceImpl(appContext)
    }


    @Provides
    @Singleton
    fun getGame(dataSource: DataSource): Game {
        return Game(dataSource)
    }

}