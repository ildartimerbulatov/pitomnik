import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileHandler {
    private static final String CSV_FILE = "C:/Users/ildar/OneDrive/Desktop/pitomnik/Animals/Human_Friends.csv";

    public static List<Animal> loadAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isFirstLine = true; // Флаг для пропуска первой строки
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Для преобразования строки в Date
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Пропускаем первую строку (заголовок)
                    continue;
                }

                String[] data = line.split(",");
                int inventoryNumber = Integer.parseInt(data[0]);
                String species = data[1];
                Date birthDate = sdf.parse(data[2]); // Преобразуем строку в Date
                List<String> commands = Arrays.asList(data[3].replace("\"", "").split(", "));
                String nickname = data[4];
                String purpose = data[5];

                animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
            }
        } catch (IOException | ParseException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return animals;
    }

    public static void saveAnimals(List<Animal> animals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            // Записываем заголовок
            writer.write("inventory_number,species,birth_date,commands,nickname,purpose");
            writer.newLine();

            // Записываем данные
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}