package com.mohit.machineexpensetracker.di

import android.content.Context
import androidx.room.Room
import com.mohit.machineexpensetracker.data.local.ExpenseDao
import com.mohit.machineexpensetracker.data.local.ExpenseDatabase
import com.mohit.machineexpensetracker.data.repository.ExpenseRepositoryImpl
import com.mohit.machineexpensetracker.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ): ExpenseDatabase{
        return Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "expense_tracker"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(
        database: ExpenseDatabase
    ): ExpenseDao{
        return database.getExpenseDao()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(
        dao: ExpenseDao
    ): ExpenseRepository{
        return ExpenseRepositoryImpl(dao)
    }



}