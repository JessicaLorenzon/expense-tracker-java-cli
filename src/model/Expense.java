package model;

import java.time.LocalDate;

public class Expense {

    private final Integer id;
    private String description;
    private Double amount;
    private LocalDate date;

    public Expense(Integer id, String description, Double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
