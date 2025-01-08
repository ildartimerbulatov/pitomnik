import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AnimalService animalService = new AnimalService();
        List<Animal> animals = animalService.loadAnimals();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.println("\nМеню реестра животных:");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Обучить животное новым командам");
            System.out.println("3. Вывести список животных по дате рождения");
            System.out.println("4. Показать общее количество животных");
            System.out.println("5. Удалить или изменить животное по ID");
            System.out.println("6. Вывести весь список животных");
            System.out.println("7. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> animalService.addAnimal(scanner, animals);
                case 2 -> animalService.trainAnimal(scanner, animals);
                case 3 -> animalService.listByBirthDate(animals);
                case 4 -> System.out.println("Общее количество животных: " + animalService.getTotalAnimals());
                case 5 -> animalService.modifyAnimal(scanner, animals);
                case 6 -> animalService.listAllAnimals(animals);
                case 7 -> {
                    animalService.saveAnimals(animals);
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
