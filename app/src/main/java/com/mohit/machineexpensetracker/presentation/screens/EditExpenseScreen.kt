package com.mohit.machineexpensetracker.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mohit.machineexpensetracker.presentation.viewmodel.ExpenseViewModel

@Composable
fun EditExpenseScreen(expenseId : Int, onBack:()->Unit) {

    val viewmodel: ExpenseViewModel = hiltViewModel()
    val selectedExpense = viewmodel.selectedExpense.collectAsState().value
    val query = viewmodel.searchQuery.collectAsState().value

    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf("Food") }
    var expanded by remember { mutableStateOf(false) }
    val category = listOf(
        "Food",
        "Pet",
        "Animal",
        "Shopping"
    )

    LaunchedEffect(expenseId) {
        viewmodel.getExpenseById(expenseId)
    }

    LaunchedEffect(selectedExpense) {
        selectedExpense?.let {
            title = it.title
            amount = it.amount.toString()
            categories = it.category
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it }
        )
        Spacer(Modifier.heightIn(10.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it }
        )
        Spacer(Modifier.heightIn(10.dp))

        Button(onClick = { expanded = true }) { Text(categories) }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            category.forEach { items ->
                DropdownMenuItem(
                    text = {
                        Text(items)
                    }, onClick = {
                        categories = items
                        expanded = false
                    }
                )
            }

        }

        Button(onClick = {
            selectedExpense?.let {

            viewmodel.updateExpense(
                   it.copy(
                       title = it.title
                       ,amount = it.amount,
                       category = it.category
                   ))
                }
            onBack()
        }) { Text("Edit Expenses") }









    }












    }