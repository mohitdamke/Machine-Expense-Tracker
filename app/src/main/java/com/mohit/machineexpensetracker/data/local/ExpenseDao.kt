package com.mohit.machineexpensetracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_tracker ORDER BY id DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_tracker WHERE id = :id ")
    fun getExpenseById(id: Int): Flow<ExpenseEntity?>

    @Query(
        """
        SELECT * FROM expense_tracker
        WHERE title LIKE '%' || :query || '%'
        OR category LIKE '%' || :query || '%'
        ORDER BY id DESC
    """
    )
    fun searchExpense(query: String): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertExpense(entity: ExpenseEntity)

    @Update
    suspend fun updateExpense(entity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(entity: ExpenseEntity)

}