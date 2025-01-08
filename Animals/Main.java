import java.util.*;

public class Main {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addAnimal(registry, scanner);
                case 2 -> registry.printAnimalsByBirthDate();
                case 3 -> registry.printAllAnimals();
                case 4 -> System.out.println("Общее количество животных: " + registry.getAnimalCount());
                case 5 -> deleteAnimal(registry, scanner);
                case 6 -> {
                    registry.saveData();
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
        System.out.println("6. Выйти");
        System.out.print("Выберите опцию: ");
    }

    private static void addAnimal(AnimalRegistry registry, Scanner scanner) {
        System.out.print("Введите вид животного: ");
        String species = scanner.nextLine();

        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        String birthDate = scanner.nextLine();

        System.out.print("Введите кличку: ");
        String nickname = scanner.nextLine();

        System.out.print("Введите назначение (pets/Pack): ");
        String purpose = scanner.nextLine();

        System.out.print("Введите команды (через запятую): ");
        List<String> commands = Arrays.asList(scanner.nextLine().split(", "));

        registry.addAnimal(species, birthDate, commands, nickname, purpose);
        System.out.println("Животное добавлено.");
    }

    private static void deleteAnimal(AnimalRegistry registry, Scanner scanner) {
        System.out.print("Введите inventory_number животного для удаления: ");
        int inventoryNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        registry.deleteAnimal(inventoryNumber);
        System.out.println("Запись удалена.");
    }
}