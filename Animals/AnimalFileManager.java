import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AnimalFileManager {
    private static final String FILE_PATH = "C:/Users/ildar/OneDrive/Desktop/pitomnik/Animals/Human Friends.txt";  // абсолютный путь

    // Считать животных из файла
    public static List<Animal> readAnimals() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Пропускаем заголовок
                if (line.startsWith("id,")) {
                    continue;
                }

                // Очищаем строку от некорректных символов (если нужно, но имейте в виду возможные проблемы с кодировкой)
                line = line.replaceAll("[^\\x00-\\x7F]", "");  // Удаляем все не-ASCII символы

                // Убираем лишние пробелы и разделяем строку
                String[] parts = line.trim().split("\\s*,\\s*");
                if (parts.length == 5) {
                    animals.add(new Animal(
                        parts[0].trim(), // ID теперь строка
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim()
                    ));
                } else {
                    System.out.println("Неверный формат данных: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования числа: " + e.getMessage());
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
