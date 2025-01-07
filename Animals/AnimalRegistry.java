import java.io.*;
import java.util.*;

public class AnimalRegistry {
    // Список животных
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        // Загрузка животных из файла
        loadAnimalsFromFile("C:/Users/ildar/OneDrive/Desktop/pitomnik/Animals/Human_Friends.csv");

        // Меню
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Меню:");
                System.out.println("1. Показать всех животных");
                System.out.println("2. Добавить новое животное");
                System.out.println("3. Обучить животное новой команде");
                System.out.println("4. Показать количество животных");
                System.out.println("5. Выйти");
                System.out.print("Выберите пункт: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Для захвата новой строки после ввода числа

                switch (choice) {
                    case 1:
                        showAllAnimals();
                        break;
                    case 2:
                        addNewAnimal(scanner);
                        break;
                    case 3:
                        teachAnimalNewCommand(scanner);
                        break;
                    case 4:
                        showAnimalCount();
                        break;
                    case 5:
                        System.out.println("Выход из программы.");
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void loadAnimalsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(","); // Разделение строки по запятой

                    if (parts.length >= 5) {  // Убедитесь, что строка имеет достаточно данных
                        String id = parts[0].trim();
                        String name = parts[1].trim();
                        String birthDate = parts[2].trim();
                        String commands = parts[3].trim();
                        String type = parts[4].trim();

                        // Создание и добавление объекта Animal
                        animals.add(new Animal(id, name, birthDate, commands, type));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static void showAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Нет животных для отображения.");
        } else {
            for (Animal animal : animals) {
                System.out.println(animal);
            }
        }
    }

    public static void addNewAnimal(Scanner scanner) {
        System.out.print("Введите ID животного: ");
        String id = scanner.nextLine();
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения животного (yyyy-MM-dd): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите команды для животного (через запятую): ");
        String commands = scanner.nextLine();
        System.out.print("Введите тип животного: ");
        String type = scanner.nextLine();

        // Удаление лишних пробелов и кавычек
        commands = commands.replaceAll("\"", "").trim();

        Animal newAnimal = new Animal(id, name, birthDate, commands, type);
        animals.add(newAnimal);  // Добавляем новое животное в список

        // Сохраняем в файл
        AnimalFileManager.saveAnimals(animals);

        System.out.println("Животное добавлено.");
    }

    public static void teachAnimalNewCommand(Scanner scanner) {
        System.out.print("Введите ID животного для обучения новой команде: ");
        String id = scanner.nextLine();

        Animal animal = findAnimalById(id);
        if (animal != null) {
            System.out.print("Введите новую команду для животного: ");
            String newCommand = scanner.nextLine();
            animal.addCommand(newCommand);
            System.out.println("Животное обучено новой команде.");
        } else {
            System.out.println("Животное с таким ID не найдено.");
        }
    }

    public static void showAnimalCount() {
        System.out.println("Количество животных: " + animals.size());
    }

    private static Animal findAnimalById(String id) {
        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }
}
