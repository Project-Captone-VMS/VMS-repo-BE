package org.example.vmsproject.service;

import org.example.vmsproject.dto.ExpenseDTO;
import org.example.vmsproject.entity.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getAllExpenses();

    Optional<Expense> getExpenseById(Long expenseId);

    String saveExpense(ExpenseDTO DTO);

    void deleteExpense(Long expenseId);
}
