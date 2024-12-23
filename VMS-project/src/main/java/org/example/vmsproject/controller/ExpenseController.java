package org.example.vmsproject.controller;

import ch.qos.logback.classic.Logger;
import org.example.vmsproject.dto.ExpenseDTO;
import org.example.vmsproject.entity.Expense;
import org.example.vmsproject.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{expenseId}")
    public Optional<Expense> getExpenseById(@PathVariable Long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.saveExpense(expenseDTO));
    }

    @PutMapping("/{id}")
    public void createExpense(@RequestBody Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }

}
