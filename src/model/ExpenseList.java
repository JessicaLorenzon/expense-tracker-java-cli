package model;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseList {

    private final List<Expense> expenses = new ArrayList<>();
    private Integer nextId = 1;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(String description, Double amount) {
        if (description.isBlank() || amount.isNaN()) {
            System.out.println("Invalid description or amount!");
        } else {
            expenses.add(new Expense(nextId, description, amount));
            System.out.println("Expense added successfully (ID: " + nextId + ")");
            nextId++;
        }
    }

    public void deleteExpense(Integer id) {
        Expense expense = searchById(id);
        if (expense != null) {
            expenses.remove(expense);
            System.out.println("Expense with id " + id + " deleted successfully");
        }
    }

    public void updateExpense(Integer id, String description, Double amount) {
        Expense expense = searchById(id);
        if (expense != null) {
            if (description.isBlank() || amount.isNaN()) {
                System.out.println("Invalid description or amount!");
            } else {
                expense.setDescription(description);
                expense.setAmount(amount);
                System.out.println("Expense with id " + id + " updated successfully");
            }
        }
    }

    public void listAllExpenses() {
        System.out.println("ID  Date        Description     Amount");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void sumaryAllExpenses() {
        Double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total expenses: $" + total);
    }

    public void sumaryMonthExpenses(Integer month) {
        Double total = 0.0;
        if (month < 1 || month > 12) {
            System.out.println("Invalid month!");
        } else {
            for (Expense expense : expenses) {
                if (month.equals(expense.getDate().getMonthValue())) {
                    total += expense.getAmount();
                }
            }
            System.out.println("Total expenses for " + monthConverter(month) + ": $" + total);
        }
    }

    private Expense searchById(Integer id) {
        for (Expense expense : expenses) {
            if (id.equals(expense.getId())) {
                return expense;
            }
        }
        System.out.println("Expense with id " + id + " not found");
        return null;
    }

    private String monthConverter(Integer month) {
        Month monthEnum = Month.of(month);
        return monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
