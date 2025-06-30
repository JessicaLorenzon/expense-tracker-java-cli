package application;

import model.Expense;
import model.ExpenseList;

public class Main {
    public static void main(String[] args) {

        ExpenseList expenses = new ExpenseList();

        Expense expense1 = new Expense(1, "conta luz", 50.00);
        Expense expense2 = new Expense(2, "conta agua", 40.00);

        expenses.addExpense(expense1);
        expenses.addExpense(expense2);

        System.out.println(expenses);
    }
}
