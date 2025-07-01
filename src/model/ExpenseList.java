package model;

import view.View;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseList {

    private final List<Expense> expenses = new ArrayList<>();
    private Integer nextId = 1;

    View view = new View();

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(String description, Double amount) {
        if (description.isBlank() || amount.isNaN()) {
            view.displayInvalidParameter();
        } else {
            expenses.add(new Expense(nextId, description, amount));
            view.displayAddedSuccessfully(nextId);
            nextId++;
        }
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
        if (expenseUpdated != null) {
            if (description.isBlank() || amount.isNaN()) {
                view.displayInvalidParameter();
            } else {
                expenseUpdated.setDescription(description);
                expenseUpdated.setAmount(amount);
                view.displayUpdatedSuccessfully(id);
            }
        }
    }

    public void listAllExpenses() {
        view.displayHeaderExpenses();
        for (Expense expense : expenses) {
            System.out.println(view.displayExpense(expense));
        }
    }

    public void sumaryAllExpenses() {
        Double totalAll = 0.0;
        for (Expense expense : expenses) {
            totalAll += expense.getAmount();
        }
        view.displayTotalAll(totalAll);
    }

    public void sumaryMonthExpenses(Integer month) {
        Double totalMonth = 0.0;
        if (month < 1 || month > 12) {
            view.displayInvalidMonth();
        } else {
            for (Expense expense : expenses) {
                if (month.equals(expense.getDate().getMonthValue())) {
                    totalMonth += expense.getAmount();
                }
            }
            view.displayTotalMonth(monthConverter(month), totalMonth);
        }
    }

    private Expense searchById(Integer id) {
        for (Expense expense : expenses) {
            if (id.equals(expense.getId())) {
                return expense;
            }
        }
        view.displayInvalidId(id);
        return null;
    }

    private String monthConverter(Integer month) {
        Month monthEnum = Month.of(month);
        return monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
