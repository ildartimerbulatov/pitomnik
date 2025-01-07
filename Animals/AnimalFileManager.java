import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AnimalFileManager {
    private static final String FILE_PATH = "Animals/Human Friends.txt";

    // Считать животных из файла
    public static List<Animal> readAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("id,")) continue; // Пропустить заголовок

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

    // Добавить животное в файл
    public static void addAnimal(Animal animal) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND)) {
            writer.write(animal.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Обновить файл с животными
    public static void saveAnimals(List<Animal> animals) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            writer.write("id, name, birth_date, commands, вид");
            writer.newLine();
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
