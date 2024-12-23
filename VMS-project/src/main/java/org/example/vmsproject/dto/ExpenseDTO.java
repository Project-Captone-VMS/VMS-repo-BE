package org.example.vmsproject.dto;

import lombok.Data;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ExpenseDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private Vehicle vehicle;
    private Driver driver;
}
