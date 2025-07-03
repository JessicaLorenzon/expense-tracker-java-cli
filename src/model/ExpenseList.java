package model;

import view.View;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {

    private final List<Expense> expenses = new ArrayList<>();
    private Integer nextId = 1;

    private final View view = new View();

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void loadExistingExpenses(List<Expense> loadedExpenses) {
        expenses.addAll(loadedExpenses);

        int maxId = 0;
        for (Expense expense : loadedExpenses) {
            if (expense.getId() > maxId) {
                maxId = expense.getId();
            }
        }
        nextId = maxId + 1;
    }

    public void addExpense(String description, Double amount) {
        if (invalidExpense(description, amount)) {
            view.displayInvalidParameter();
            return;
        }
        expenses.add(new Expense(nextId, description, amount));
        view.displayAddedSuccessfully(nextId);
        nextId++;
    }

    public void deleteExpense(Integer id) {
        Expense expenseDeleted = searchById(id);
        if (expenseDeleted != null) {
            expenses.remove(expenseDeleted);
            view.displayDeletedSuccessfully(id);
        }
    }

    public void updateExpense(Integer id, String description, Double amount) {
        Expense expenseUpdated = searchById(id);
        if (expenseUpdated == null) return;

        if (invalidExpense(description, amount)) {
            view.displayInvalidParameter();
            return;
        }
        expenseUpdated.setDescription(description);
        expenseUpdated.setAmount(amount);
        view.displayUpdatedSuccessfully(id);
    }

    public void listAllExpenses() {
        view.displayHeaderExpenses();
        for (Expense expense : expenses) {
            view.displayExpense(expense);
        }
    }

    public void summaryAllExpenses() {
        Double totalAll = 0.0;
        for (Expense expense : expenses) {
            totalAll += expense.getAmount();
        }
        view.displayTotalAll(totalAll);
    }

    public void summaryMonthExpenses(Integer month) {
        Double totalMonth = 0.0;
        if (month < 1 || month > 12) {
            view.displayInvalidMonth();
            return;
        }
        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month) {
                totalMonth += expense.getAmount();
            }
        }
        String monthName = view.formatMonth(month);
        view.displayTotalMonth(monthName, totalMonth);
    }

    private Expense searchById(Integer id) {
        for (Expense expense : expenses) {
            if (expense.getId().equals(id)) {
                return expense;
            }
        }
        view.displayInvalidId(id);
        return null;
    }

    private boolean invalidExpense(String description, Double amount) {
        return description == null || description.isBlank() || amount == null || amount.isNaN();
    }
}
