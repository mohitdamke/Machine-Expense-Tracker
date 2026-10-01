package com.mohit.machineexpensetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohit.machineexpensetracker.data.local.ExpenseEntity
import com.mohit.machineexpensetracker.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()
    private val _selectedExpense = MutableStateFlow<ExpenseEntity?>(null)
    val selectedExpense = _selectedExpense.asStateFlow()
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    private val _isError = MutableStateFlow<String>("")
    val isError = _isError.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val expenses: StateFlow<List<ExpenseEntity>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isNotEmpty()) {
                repository.searchExpense(query)
            } else {
                repository.getAllExpenses()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalSumOfExpense : StateFlow<Double> = expenses
        .map { expenseEntities ->
            expenseEntities.sumOf { it.amount }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun searchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getExpenseById(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                repository.getExpenseById(id).collect { expense ->
                    _selectedExpense.value = expense
                }

                _isLoading.value = true

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            }
        }
    }

    fun insertExpense(
        title: String,
        amount: Double,
        category: String
    ) {
        viewModelScope.launch {
            try {

                repository.insertExpense(
                    ExpenseEntity(
                        title = title,
                        amount = amount,
                        category = category
                    )
                )


            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            }
        }
    }

    fun updateExpense(
        entity: ExpenseEntity
    ) {
        viewModelScope.launch {
            try {

                repository.updateExpense(entity)


            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            }
        }
    }


    fun deleteExpense(
        entity: ExpenseEntity
    ) {
        viewModelScope.launch {
            try {

                repository.deleteExpense(entity)


            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            }
        }
    }


}
















