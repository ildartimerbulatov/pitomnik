import java.util.*;

public class AnimalRegistry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> animals = AnimalFileManager.readAnimals();

        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Показать все животные");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать общее количество животных");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAnimal(scanner, animals);
                    break;
                case "2":
                    showAnimals(animals);
                    break;
                case "3":
                    trainAnimal(scanner, animals);
                    break;
                case "4":
                    showAnimalCount(animals);
                    break;
                case "5":
                    AnimalFileManager.writeAnimals(animals);
                    System.out.println("Изменения сохранены. Выход.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Введите ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (yyyy-MM-dd): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите команды (через запятую): ");
        String commands = scanner.nextLine();
        System.out.print("Введите вид: ");
        String type = scanner.nextLine();

        animals.add(new Animal(id, name, birthDate, commands, type));
        System.out.println("Животное добавлено.");
    }

    private static void showAnimals(List<Animal> animals) {
        System.out.println("\n--- Список животных ---");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static void trainAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Введите ID животного: ");
        int id = Integer.parseInt(scanner.nextLine());

        Animal animal = animals.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElse(null);

        if (animal != null) {
            System.out.print("Введите новую команду: ");
            String newCommand = scanner.nextLine();
            animal.addCommand(newCommand);
            System.out.println("Команда добавлена.");
        } else {
            System.out.println("Животное с таким ID не найдено.");
        }
    }

    private static void showAnimalCount(List<Animal> animals) {
        System.out.println("Общее количество животных: " + animals.size());
    }
}
