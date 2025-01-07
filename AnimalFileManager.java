import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AnimalFileManager {
    private static final String FILE_PATH = "Animals/Human Friends.txt";

    public static List<Animal> readAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Пропускаем строку, если она содержит заголовок
                if (line.startsWith("id,")) {
                    continue;
                }

                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    animals.add(new Animal(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return animals;
    }

    public static void main(String[] args) {
        List<Animal> animals = readAnimals();
        System.out.println("Список животных из файла:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
