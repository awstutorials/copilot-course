package com.expenses.expensetracker;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.expenses.expensetracker.domain.Expense;
import com.expenses.expensetracker.repository.ExpenseRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ExpenseRepository expenseRepository;

    public DataLoader(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadExpenses();
    }

    private void loadExpenses() {
        for (int i = 0; i < 5; i++) {
            Expense expense = new Expense();
            expense.setAmount(100);
            expense.setCategory("Category " + i);
            expense.setCurrency("USD");
            expense.setExpenseDate(LocalDate.now());
            expense.setDescription("Expense " + i);
            // Set properties of the expense
            expenseRepository.save(expense);
        }
    }
}