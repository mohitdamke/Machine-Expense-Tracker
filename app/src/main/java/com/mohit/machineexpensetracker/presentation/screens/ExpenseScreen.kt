package com.mohit.machineexpensetracker.presentation.screens

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mohit.machineexpensetracker.presentation.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(onAdd: () -> Unit, onEdit: (Int) -> Unit) {

    val viewmodel: ExpenseViewModel = hiltViewModel()
    val expense = viewmodel.expenses.collectAsState().value
    val query = viewmodel.searchQuery.collectAsState().value
    val totalExpense = viewmodel.totalSumOfExpense.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = viewmodel::searchQuery
        )
        Spacer(Modifier.heightIn(10.dp))
        Text(text = "Total Expense : ${"%.2f".format(totalExpense)}")
        Spacer(Modifier.heightIn(10.dp))

        Button(onClick = { onAdd() }) { Text("Add +") }

        Spacer(Modifier.heightIn(10.dp))

        LazyColumn() {
            items(expense) { expenses ->
                Text(expenses.title)
                Text(expenses.amount.toString())
                Text(expenses.category)
                Button(onClick = { onEdit(expenses.id.toInt()) }) { Text("Edit") }
                Button(onClick = { viewmodel.deleteExpense(expenses) }) { Text("Delete") }

            }
        }


    }


}