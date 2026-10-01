package com.mohit.machineexpensetracker.domain.repository

import com.mohit.machineexpensetracker.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun getAllExpenses(): Flow<List<ExpenseEntity>>

     fun getExpenseById(id: Int): Flow<ExpenseEntity?>

    fun searchExpense(query: String): Flow<List<ExpenseEntity>>

    suspend fun insertExpense(entity: ExpenseEntity)

    suspend fun updateExpense(entity: ExpenseEntity)

    suspend fun deleteExpense(entity: ExpenseEntity)
}