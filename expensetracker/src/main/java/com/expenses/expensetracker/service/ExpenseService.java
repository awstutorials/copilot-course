/**
 * This class represents the ExpenseService, which is responsible for handling operations related to expenses.
 * It provides methods to retrieve, create, update, and delete expenses.
 */
package com.expenses.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.expensetracker.domain.Expense;
import com.expenses.expensetracker.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * Updates an expense with the given ID using the provided expense details.
     *
     * @param id             The ID of the expense to be updated.
     * @param expenseDetails The updated expense details.
     * @return The updated expense, or null if the expense with the given ID does not exist.
     */
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null) {
            expense.setAmount(expenseDetails.getAmount());
            expense.setCategory(expenseDetails.getCategory());
            expense.setExpenseDate(expenseDetails.getExpenseDate());
            expense.setCurrency(expenseDetails.getCurrency());
            expense.setDescription(expenseDetails.getDescription()); // Fix: Use expenseDetails.getDescription() instead of expenseDetails.getCategory()
            // update other fields as needed
            return expenseRepository.save(expense);
        }
        return null;
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
