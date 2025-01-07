import java.util.*;

public class AnimalRegistry {
    private static int animalCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> animals = AnimalFileManager.readAnimals();
        animalCount = animals.size();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать всех животных");
            System.out.println("2. Добавить новое животное");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать количество животных");
            System.out.println("5. Выйти");
            System.out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистить буфер

            switch (choice) {
                case 1:
                    System.out.println("Список животных:");
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                    break;
                case 2:
                    System.out.print("Введите id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите дату рождения (YYYY-MM-DD): ");
                    String birthDate = scanner.nextLine();
                    System.out.print("Введите команды (через запятую): ");
                    String commands = scanner.nextLine();
                    System.out.print("Введите вид: ");
                    String type = scanner.nextLine();

                    Animal newAnimal = new Animal(id, name, birthDate, commands, type);
                    animals.add(newAnimal);
                    AnimalFileManager.addAnimal(newAnimal);
                    animalCount++;
                    System.out.println("Животное добавлено.");
                    break;
                case 3:
                    System.out.print("Введите id животного для обучения: ");
                    int targetId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новую команду: ");
                    String newCommand = scanner.nextLine();

                    for (Animal animal : animals) {
                        if (animal.getId() == targetId) {
                            animal.addCommand(newCommand);
                            System.out.println("Животное обучено новой команде.");
                        }
                    }
                    AnimalFileManager.saveAnimals(animals);
                    break;
                case 4:
                    System.out.println("Общее количество животных: " + animalCount);
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
