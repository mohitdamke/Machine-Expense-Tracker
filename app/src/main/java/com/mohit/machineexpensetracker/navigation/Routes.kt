package com.mohit.machineexpensetracker.navigation

import android.util.Log.d

object Routes {
    const val Expense = "expense_list"
    const val AddExpense = "add_expense_list"
    const val EditExpense = "edit_expense_list/{id}"
    fun editExpense(id : Int) =  "edit_expense_list/$id"
}