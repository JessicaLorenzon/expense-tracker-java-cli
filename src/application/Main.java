package application;

import model.ExpenseList;
import service.JsonService;

public class Main {
    public static void main(String[] args) {

        JsonService jsonService = new JsonService();

        ExpenseList expenses = jsonService.loadExpenses();

        expenses.addExpense("Lunch", 20.00);
        expenses.addExpense("Dinner", 40.00);
        expenses.addExpense("TV", 30.00);
        expenses.addExpense("Movies", 15.00);

//        expenses.deleteExpense(3);

//        expenses.updateExpense(2, "Games", 25.00);

//        expenses.listAllExpenses();

//        expenses.sumaryAllExpenses();

//        expenses.sumaryMonthExpenses(7);

        jsonService.saveExpenses(expenses);
    }
}
