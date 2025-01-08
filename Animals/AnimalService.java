import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class AnimalService {
    private static final String FILE_PATH = "C:\\Users\\ildar\\OneDrive\\Desktop\\pitomnik\\Animals\\Human_Friends.csv";
    private static final String DELIMITER = ",";
    private int totalAnimals = 0;

    public List<Animal> loadAnimals() throws IOException {
        List<String[]> lines = Files.lines(Paths.get(FILE_PATH), java.nio.charset.StandardCharsets.UTF_8)
            .map(line -> line.split(DELIMITER))
            .collect(Collectors.toList());
        lines.remove(0); // Remove header
    
        List<Animal> animals = new ArrayList<>();
        for (String[] line : lines) {
            animals.add(new Animal(
                line[0].trim(),
                line[1].trim(),
                LocalDate.parse(line[2].trim()), // Удаляем пробелы перед парсингом даты
                line[3].trim(),
                line[4].trim()
            ));
        }
        totalAnimals = animals.size();
        return animals;
    }

    public void saveAnimals(List<Animal> animals) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,birth_date,commands,вид"); // Header
        for (Animal animal : animals) {
            lines.add(animal.toString());
        }
        Files.write(Paths.get(FILE_PATH), lines, java.nio.charset.StandardCharsets.UTF_8);
    }
    public void listAllAnimals(List<Animal> animals) {
        System.out.println("\nСписок всех животных:");
        if (animals.isEmpty()) {
            System.out.println("Реестр пуст.");
        } else {
            animals.forEach(System.out::println);
        }
    }
    public void addAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Введите ID: ");
        String id = scanner.nextLine();
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите команды (через запятую): ");
        String commands = scanner.nextLine();
        System.out.print("Введите тип (домашнее/вьючное): ");
        String type = scanner.nextLine();

        animals.add(new Animal(id, name, LocalDate.parse(birthDate), commands, type));
        totalAnimals++;
        System.out.println("Животное успешно добавлено.");
    }

    public void trainAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Введите ID животного: ");
        String id = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                System.out.print("Введите новые команды (через запятую): ");
                String newCommands = scanner.nextLine();
                animal.setCommands(animal.getCommands() + ", " + newCommands);
                System.out.println("Животное успешно обучено.");
                return;
            }
        }
        System.out.println("Животное с таким ID не найдено.");
    }

    public void listByBirthDate(List<Animal> animals) {
        animals.stream()
            .sorted(Comparator.comparing(Animal::getBirthDate))
            .forEach(System.out::println);
    }

    public void modifyAnimal(Scanner scanner, List<Animal> animals) {
        System.out.print("Введите ID животного для удаления или редактирования: ");
        String id = scanner.nextLine();

        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.getId().equals(id)) {
                System.out.println("1. Удалить животное");
                System.out.println("2. Изменить животное");
                System.out.print("Выберите действие: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    iterator.remove();
                    totalAnimals--;
                    System.out.println("Животное успешно удалено.");
                } else if (choice == 2) {
                    System.out.print("Введите новое имя: ");
                    animal.setName(scanner.nextLine());
                    System.out.print("Введите новую дату рождения (ГГГГ-ММ-ДД): ");
                    animal.setBirthDate(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Введите новые команды (через запятую): ");
                    animal.setCommands(scanner.nextLine());
                    System.out.print("Введите новый тип (домашнее/вьючное): ");
                    animal.setType(scanner.nextLine());
                    System.out.println("Животное успешно обновлено.");
                } else {
                    System.out.println("Неверный выбор.");
                }
                return;
            }
        }
        System.out.println("Животное с таким ID не найдено.");
    }

    public int getTotalAnimals() {
        return totalAnimals;
    }
}
