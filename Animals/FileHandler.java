import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    private static final String CSV_FILE = "Human_Friends.csv";

    public static List<Animal> loadAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int inventoryNumber = Integer.parseInt(data[0]);
                String species = data[1];
                String birthDate = data[2];
                List<String> commands = Arrays.asList(data[3].replace("\"", "").split(", "));
                String nickname = data[4];
                String purpose = data[5];

                animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return animals;
    }

    public static void saveAnimals(List<Animal> animals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}