package view;

import model.Expense;

import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class View {

    public void displayHeaderExpenses() {
        System.out.println("ID  Date        Description     Amount");
    }

    public void displayExpense(Expense expense) {
        System.out.printf("%-3d %-11s %-15s $%.2f%n",
                expense.getId(),
                expense.getDate(),
                expense.getDescription(),
                expense.getAmount());
    }

    public void displayAddedSuccessfully(Integer id) {
        System.out.println("Expense added successfully (ID: " + id + ")");
    }

    public void displayDeletedSuccessfully(Integer id) {
        System.out.println("Expense with id " + id + " deleted successfully");
    }

    public void displayUpdatedSuccessfully(Integer id) {
        System.out.println("Expense with id " + id + " updated successfully");
    }

    public void displayTotalAll(Double totalAll) {
        System.out.println("Total expenses: $" + totalAll);
    }

    public void displayTotalMonth(String month, Double totalMonth) {
        System.out.println("Total expenses for " + month + ": $" + totalMonth);
    }

    public void displayInvalidParameter() {
        System.out.println("Invalid description or amount!");
    }

    public void displayInvalidMonth() {
        System.out.println("Invalid month!");
    }

    public void displayInvalidId(Integer id) {
        System.out.println("Expense with id " + id + " not found");
    }

    public void displayInvalidArgs() {
        System.out.println("Invalid argument! Try again");
    }

    public void displaySavingError(IOException e) {
        System.err.println("Error saving expenses: " + e.getMessage());
    }

    public void displayLoadingError(IOException e) {
        System.err.println("Error loading expenses: " + e.getMessage());
    }

    public String formatMonth(Integer month) {
        Month monthEnum = Month.of(month);
        return monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
