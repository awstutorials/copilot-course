package com.expenses.expensetracker.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.expenses.expensetracker.domain.Expense;
import com.expenses.expensetracker.service.ExpenseService;

public class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenses() {
        // Arrange
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, LocalDate.of(2021, 1, 1), "Expense 1", 100.0, "Category 1", "USD"));
        when(expenseService.getAllExpenses()).thenReturn(expenses);

        // Act
        ResponseEntity<Iterable<Expense>> response = expenseController.getAllExpenses();

        // Assert
        verify(expenseService).getAllExpenses();
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == expenses;
    }

    @Test
    public void testGetExpenseById() {
        // Arrange
        Long id = 1L;
        Expense expense = new Expense(id, LocalDate.of(2021, 1, 1), "Expense 1", 100.0, "Category 1", "USD");
        when(expenseService.getExpenseById(id)).thenReturn(expense);

        // Act
        ResponseEntity<Expense> response = expenseController.getExpenseById(id);

        // Assert
        verify(expenseService).getExpenseById(id);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == expense;
    }

    @Test
    public void testCreateExpense() {
        // Arrange
        Expense expense = new Expense(1L, LocalDate.of(2021, 1, 1), "Expense 1", 100.0, "Category 1", "USD");
        when(expenseService.createExpense(expense)).thenReturn(expense);

        // Act
        Expense createdExpense = expenseController.createExpense(expense);

        // Assert
        verify(expenseService).createExpense(expense);
        assert createdExpense == expense;
    }

    @Test
    public void testUpdateExpense() {
        // Arrange
        Long id = 1L;
        Expense expenseDetails = new Expense(id, LocalDate.of(2021, 1, 1), "Expense 1", 100.0, "Category 1", "USD");
        Expense updatedExpense = new Expense(id, LocalDate.of(2021, 1, 1), "Updated Expense", 200.0, "Category 2", "EUR");
        when(expenseService.updateExpense(id, expenseDetails)).thenReturn(updatedExpense);

        // Act
        ResponseEntity<Expense> response = expenseController.updateExpense(id, expenseDetails);

        // Assert
        verify(expenseService).updateExpense(id, expenseDetails);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == updatedExpense;
    }

    @Test
    public void testDeleteExpense() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = expenseController.deleteExpense(id);

        // Assert
        verify(expenseService).deleteExpense(id);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }
}
