import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private static final String FILE_NAME = "Human Friends.txt";
    private List<Animal> animals = new ArrayList<>();
    private int animalCount = 0;

    public void loadAnimals() {
        System.out.println("Текущий рабочий каталог: " + new File(".").getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                animals.add(parseAnimal(line));
                animalCount++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    private Animal parseAnimal(String line) {
        String[] parts = line.split(", ");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String birthDate = parts[2];
        List<String> commands = new ArrayList<>();
        for (int i = 3; i < parts.length - 1; i++) {
            commands.add(parts[i]);
        }
        String type = parts[parts.length - 1];
        return new Animal(id, name, birthDate, commands, type);
    }

    public void saveAnimals() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Animal animal : animals) {
                bw.write(animal.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public void addAnimal(Scanner scanner) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();
        List<String> commands = getCommandsFromUser(scanner);
        System.out.print("Введите вид животного: ");
        String type = scanner.nextLine();

        int id = generateAnimalId();
        animals.add(new Animal(id, name, birthDate, commands, type));
        animalCount++;
        System.out.println("Животное добавлено.");
    }

    private List<String> getCommandsFromUser(Scanner scanner) {
        System.out.print("Введите команды (через запятую): ");
        String[] commandsArray = scanner.nextLine().split(", ");
        List<String> commands = new ArrayList<>();
        for (String command : commandsArray) {
            commands.add(command);
        }
        return commands;
    }

    private int generateAnimalId() {
        return 1000 + animalCount + 1; // Генерация ID
    }

    public void displayAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Нет животных для отображения.");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void trainAnimal(Scanner scanner) {
        System.out.print("Введите ID животного для обучения: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Animal animal = findAnimalById(id);
        if (animal != null) {
            System.out.print("Введите новую команду: ");
            String command = scanner.nextLine();
            animal.addCommand(command);
            System.out.println("Команда добавлена.");
        } else {
            System.out.println("Животное с таким ID не найдено.");
        }
    }

    private Animal findAnimalById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null; // Если животное не найдено
    }

    public void displayAnimalCount() {
        System.out.println("Общее количество животных: " + animalCount);
    }

    public void updateAnimal(Scanner scanner) {
        System.out.print("Введите ID животного для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Animal animal = findAnimalById(id);
        
        if (animal != null) {
            System.out.println("Текущие данные: " + animal);
            System.out.print("Введите новое имя (или нажмите Enter для сохранения текущего): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                animal.setName(newName);
            }

            System.out.print("Введите новую дату рождения (или нажмите Enter для сохранения текущей): ");
            String newBirthDate = scanner.nextLine();
            if (!newBirthDate.isEmpty()) {
                animal.setBirthDate(newBirthDate);
            }

            System.out.print("Введите новые команды (через запятую, или нажмите Enter для сохранения текущих): ");
            String newCommandsInput = scanner.nextLine();
            if (!newCommandsInput.isEmpty()) {
                List<String> newCommands = List.of(newCommandsInput.split(", "));
                animal.setCommands(newCommands);
            }

            System.out.println("Данные животного обновлены.");
        } else {
            System.out.println("Животное с таким ID не найдено.");
        }
    }
}

