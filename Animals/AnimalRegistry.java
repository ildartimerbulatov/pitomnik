import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    // Список животных
    private static List<Animal> animals = new ArrayList<>();
    // Счетчик животных
    private static int animalCount = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Меню
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
            System.out.println("Ошибка: " + e.getMessage()); // Исправлено getMessa на getMessage
        }
    }

    // Метод для отображения всех животных
    private static void showAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Нет животных для отображения.");
        } else {
            for (Animal animal : animals) {
                System.out.println(animal);
            }
        }
    }

    // Метод для добавления нового животного
    private static void addNewAnimal(Scanner scanner) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите тип животного (например, собака, кошка): ");
        String type = scanner.nextLine();
        Animal newAnimal = new Animal(name, type);
        animals.add(newAnimal);
        animalCount++;
        System.out.println("Животное добавлено: " + newAnimal);
    }

    // Метод для обучения животного новой команде
    private static void teachAnimalNewCommand(Scanner scanner) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        Animal animal = findAnimalByName(name);
        if (animal != null) {
            System.out.print("Введите новую команду: ");
            String command = scanner.nextLine();
            animal.addCommand(command);
            System.out.println("Животное теперь выполняет команду: " + command);
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    // Метод для поиска животного по имени
    private static Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    // Метод для отображения количества животных
    private static void showAnimalCount() {
        System.out.println("Количество животных: " + animalCount);
    }
}
