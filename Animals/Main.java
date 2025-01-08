import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String CSV_FILE = "C:/Users/ildar/OneDrive/Desktop/pitomnik/Animals/Human_Friends.csv";
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        loadAnimalsFromCSV();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addAnimal(scanner);
                case 2 -> printAnimalsByBirthDate(scanner);
                case 3 -> printAllAnimals();
                case 4 -> printAnimalCount();
                case 5 -> deleteAnimal(scanner);
                case 6 -> {
                    saveAnimalsToCSV();
                    System.out.println("Изменения сохранены в файл.");
                }
                case 7 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить новое животное");
        System.out.println("2. Вывести список животных по дате рождения");
        System.out.println("3. Вывести всю таблицу");
        System.out.println("4. Вывести общее количество животных");
        System.out.println("5. Удалить запись");
        System.out.println("6. Сохранить изменения в файл");
        System.out.println("7. Выйти");
        System.out.print("Выберите опцию: ");
    }

    private static void loadAnimalsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean isFirstLine = true; // Флаг для пропуска первой строки
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Пропускаем первую строку
                    continue;
                }

                String[] data = line.split(",");
                int inventoryNumber = Integer.parseInt(data[0]);
                String species = data[1];
                Date birthDate = sdf.parse(data[2]);
                List<String> commands = Arrays.asList(data[3].replace("\"", "").split(", "));
                String nickname = data[4];
                String purpose = data[5];

                animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
            }
        } catch (IOException | ParseException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void saveAnimalsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static void addAnimal(Scanner scanner) {
        System.out.print("Введите вид животного: ");
        String species = scanner.nextLine();

        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        String birthDateStr = scanner.nextLine();
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Используйте формат гггг-мм-дд.");
            return;
        }

        System.out.print("Введите кличку: ");
        String nickname = scanner.nextLine();

        System.out.print("Введите назначение (pets/Pack): ");
        String purpose = scanner.nextLine();

        System.out.print("Введите команды (через запятую): ");
        List<String> commands = Arrays.asList(scanner.nextLine().split(", "));

        int inventoryNumber = animals.size() + 1;
        animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
        System.out.println("Животное добавлено.");
    }

    private static void printAnimalsByBirthDate(Scanner scanner) {
        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        String birthDateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("\nЖивотные с датой рождения " + birthDateStr + ":");
        for (Animal animal : animals) {
            if (sdf.format(animal.getBirthDate()).equals(birthDateStr)) {
                System.out.println(animal);
            }
        }
    }

    private static void printAllAnimals() {
        System.out.println("\nВся таблица животных:");
        animals.forEach(System.out::println);
    }

    private static void printAnimalCount() {
        System.out.println("\nОбщее количество животных: " + animals.size());
    }

    private static void deleteAnimal(Scanner scanner) {
        System.out.print("Введите inventory_number животного для удаления: ");
        int inventoryNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        animals.removeIf(animal -> animal.getInventoryNumber() == inventoryNumber);
        System.out.println("Запись удалена.");
    }
}