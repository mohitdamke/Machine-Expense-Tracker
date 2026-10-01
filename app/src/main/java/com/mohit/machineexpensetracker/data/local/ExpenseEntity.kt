package com.mohit.machineexpensetracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_tracker")
data class ExpenseEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val title : String,
    val amount : Double,
    val category : String,


)
