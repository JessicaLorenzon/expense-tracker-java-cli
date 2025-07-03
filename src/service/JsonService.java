package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Expense;
import util.LocalDateSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonService {

    private static final String filePath = new File("expenses.json").getAbsolutePath();

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

    public void saveExpenses(List<Expense> expenses) throws IOException {
        String json = gson.toJson(expenses);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        }
    }

    public List<Expense> loadExpenses() throws IOException {
        if (Files.exists(Path.of(filePath))) {
            try (FileReader reader = new FileReader(filePath)) {
                Type tipagem = new TypeToken<List<Expense>>() {
                }.getType();
                List<Expense> expenses = gson.fromJson(reader, tipagem);
                return expenses != null ? expenses : new ArrayList<>();

            }
        }
        return new ArrayList<>();
    }
}
