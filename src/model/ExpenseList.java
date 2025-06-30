package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {

    private List<Expense> expenses = new ArrayList<>();

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public String toString() {
        return "ExpenseList{" +
                "expenses=" + expenses +
                '}';
    }
}
