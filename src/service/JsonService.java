package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.ExpenseList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class JsonService {

    public static String filePath = new File("expenses.json").getAbsolutePath();

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

    public void saveExpenses(ExpenseList expenseList) {
        String json = gson.toJson(expenseList);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
        }
    }

    public ExpenseList loadExpenses() {
        if (Files.exists(Path.of(filePath))) {
            try (FileReader reader = new FileReader(filePath)) {
                Type tipagem = new TypeToken<ExpenseList>() {
                }.getType();

                return gson.fromJson(reader, tipagem);

            } catch (IOException e) {
                System.err.println("Error loading expenses: " + e.getMessage());
                return null;
            }
        }
        return new ExpenseList();
    }
}
