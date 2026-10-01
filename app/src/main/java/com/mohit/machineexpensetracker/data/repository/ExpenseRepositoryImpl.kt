package com.mohit.machineexpensetracker.data.repository

import com.mohit.machineexpensetracker.data.local.ExpenseDao
import com.mohit.machineexpensetracker.data.local.ExpenseEntity
import com.mohit.machineexpensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<ExpenseEntity>> {
        return dao.getAllExpenses()
    }

    override  fun getExpenseById(id: Int): Flow<ExpenseEntity?> {
        return dao.getExpenseById(id)

    }

    override fun searchExpense(query: String): Flow<List<ExpenseEntity>> {
        return dao.searchExpense(query)

    }

    override suspend fun insertExpense(entity: ExpenseEntity) {
        return dao.insertExpense(entity)

    }

    override suspend fun updateExpense(entity: ExpenseEntity) {
        return dao.updateExpense(entity)

    }

    override suspend fun deleteExpense(entity: ExpenseEntity) {
        return dao.deleteExpense(entity)

    }
}