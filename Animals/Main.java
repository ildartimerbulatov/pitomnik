import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnimalRegistry animalRegistry = new AnimalRegistry();
        animalRegistry.loadAnimals();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = getUserChoice(scanner);
            handleUserChoice(choice, scanner, animalRegistry);
        } while (choice != 0);

        animalRegistry.saveAnimals();
        System.out.println("Выход из программы.");
    }

    private static void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Добавить новое животное");
        System.out.println("2. Вывести данные о животных");
        System.out.println("3. Обучить животное новой команде");
        System.out.println("4. Показать общее количество животных");
        System.out.println("5. Обновить данные о животном");
        System.out.println("0. Выход");
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Выберите опцию: ");
        return scanner.nextInt();
    }

    private static void handleUserChoice(int choice, Scanner scanner, AnimalRegistry animalRegistry) {
        scanner.nextLine(); // consume newline
        switch (choice) {
            case 1:
                animalRegistry.addAnimal(scanner);
                break;
            case 2:
                animalRegistry.displayAnimals();
                break;
            case 3:
                animalRegistry.trainAnimal(scanner);
                break;
            case 4:
                animalRegistry.displayAnimalCount();
                break;
            case 5:
                animalRegistry.updateAnimal(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
