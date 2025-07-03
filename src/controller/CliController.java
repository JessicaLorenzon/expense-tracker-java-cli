package controller;

import model.Expense;
import model.ExpenseList;
import service.JsonService;
import view.View;

import java.io.IOException;
import java.util.List;

public class CliController {

    private final View view = new View();
    private final JsonService jsonService = new JsonService();

    public void run(String[] args) {
        ExpenseList expenses = new ExpenseList();

        try {
            List<Expense> loaded = jsonService.loadExpenses();
            expenses.loadExistingExpenses(loaded);
        } catch (IOException e) {
            view.displayLoadingError(e);
            return;
        }

        boolean modified = false;

        if (args.length == 0) {
            view.displayInvalidArgs();
            return;
        }

        switch (args[0]) {
            case "add":
                modified = addValidation(args, expenses);
                break;
            case "delete":
                modified = deleteValidation(args, expenses);
                break;
            case "update":
                modified = updateValidation(args, expenses);
                break;
            case "list":
                listValidation(args, expenses);
                break;
            case "summary":
                summaryValidation(args, expenses);
                break;
            default:
                view.displayInvalidArgs();
        }

        if (modified) {
            try {
                jsonService.saveExpenses(expenses.getExpenses());
            } catch (IOException e) {
                view.displaySavingError(e);
            }
        }
    }

    private boolean addValidation(String[] args, ExpenseList expenses) {
        if (args.length >= 5 && args[1].equals("--description") && args[3].equals("--amount")) {
            try {
                String description = args[2];
                double amount = Double.parseDouble(args[4]);
                expenses.addExpense(description, amount);
                return true;
            } catch (NumberFormatException e) {
                view.displayInvalidParameter();
            }
        } else {
            view.displayInvalidArgs();
        }
        return false;
    }

    private boolean deleteValidation(String[] args, ExpenseList expenses) {
        if (args.length >= 3 && args[1].equals("--id")) {
            try {
                int id = Integer.parseInt(args[2]);
                expenses.deleteExpense(id);
                return true;
            } catch (NumberFormatException e) {
                view.displayInvalidId(Integer.parseInt(args[2]));
            }
        } else {
            view.displayInvalidArgs();
        }
        return false;
    }

    private boolean updateValidation(String[] args, ExpenseList expenses) {
        if (args.length >= 7 && args[1].equals("--id") && args[3].equals("--description") && args[5].equals("--amount")) {
            try {
                int id = Integer.parseInt(args[2]);
                String description = args[4];
                double amount = Double.parseDouble(args[6]);
                expenses.updateExpense(id, description, amount);
                return true;
            } catch (NumberFormatException e) {
                view.displayInvalidId(Integer.parseInt(args[2]));
            }
        } else {
            view.displayInvalidArgs();
        }
        return false;
    }

    private void listValidation(String[] args, ExpenseList expenses) {
        if (args.length == 1) {
            expenses.listAllExpenses();
        } else {
            view.displayInvalidArgs();
        }
    }

    private void summaryValidation(String[] args, ExpenseList expenses) {
        if (args.length == 1) {
            expenses.summaryAllExpenses();
        } else if (args.length >= 3 && args[1].equals("--month")) {
            try {
                int month = Integer.parseInt(args[2]);
                expenses.summaryMonthExpenses(month);
            } catch (NumberFormatException e) {
                view.displayInvalidMonth();
            }
        } else {
            view.displayInvalidArgs();
        }
    }
}
