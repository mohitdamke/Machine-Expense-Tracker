package com.mohit.machineexpensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohit.machineexpensetracker.presentation.screens.AddExpenseScreen
import com.mohit.machineexpensetracker.presentation.screens.EditExpenseScreen
import com.mohit.machineexpensetracker.presentation.screens.ExpenseScreen

@Composable
fun NavigationController(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.Expense
    ) {

        composable(Routes.Expense) {

            ExpenseScreen(
                onAdd = {
                    navController.navigate(Routes.AddExpense)
                },
                onEdit = {
                    navController.navigate(Routes.editExpense(it))
                }
            )

        }

        composable(Routes.AddExpense) {

            AddExpenseScreen(
                onBack = {
                    navController.popBackStack()
                },
            )

        }


        composable(Routes.EditExpense) { backstack ->
            val expenseId = backstack.arguments?.getString("id")?.toIntOrNull()
            EditExpenseScreen(
                onBack = {
                    navController.popBackStack()
                },
                expenseId = expenseId ?: 0
            )

        }


    }


}