package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.ExpenseDTO;
import org.example.vmsproject.entity.Expense;
import org.example.vmsproject.repository.ExpenseRepository;
import org.example.vmsproject.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    @Override
    public String saveExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDate(expenseDTO.getDate());
        expense.setDriver(expenseDTO.getDriver());
        expense.setVehicle(expenseDTO.getVehicle());
        expenseRepository.save(expense);
        return "Added expense successfully";
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
