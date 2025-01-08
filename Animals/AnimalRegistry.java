import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Animal {
    private int id;
    private String name;
    private String birthDate;
    private String commands;
    private String type;

    public Animal(int id, String name, String birthDate, String commands, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
        this.type = type;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCommands() {
        return commands;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", commands='" + commands + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

public class AnimalRegistry {
    private static final String FILE_PATH = "C:\\Users\\ildar\\OneDrive\\Desktop\\pitomnik\\Animals\\Human_Friends.csv";
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        loadAnimals();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Меню:");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Обучить животное новой команде");
            System.out.println("3. Вывести список животных по дате рождения");
            System.out.println("4. Вывести общее количество животных");
            System.out.println("5. Удалить животное");
            System.out.println("6. Изменить информацию о животном");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    addAnimal(scanner);
                    break;
                case 2:
                    trainAnimal(scanner);
                    break;
                case 3:
                    listAnimalsByBirthDate();
                    break;
                case 4:
                    System.out.println("Общее количество животных: " + animals.size());
                    break;
                case 5:
                    deleteAnimal(scanner);
                    break;
                case 6:
                    editAnimal(scanner);
                    break;
                case 0:
                    saveAnimals();
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 0);
    }

    private static void loadAnimals() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String line;
            br.readLine(); // Пропустить заголовок
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "").trim();
                String[] data = line.split(",\\s*");
                if (data.length == 5) {
                    try {
                        int id = Integer.parseInt(data[0]); // Преобразование id в целое число
                        animals.add(new Animal(id, data[1], data[2], data[3], data[4]));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка формата id для строки: " + line);
                    }
                } else {
                    System.out.println("Неверное количество данных в строке: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    private static void addAnimal(Scanner scanner) {
        // Реализация добавления нового животного
    }

    private static void trainAnimal(Scanner scanner) {
        // Реализация обучения животного
    }

    private static void listAnimalsByBirthDate() {
        // Реализация вывода списка животных по дате рождения
    }

    private static void deleteAnimal(Scanner scanner) {
        // Реализация удаления животного
    }

    private static void editAnimal(Scanner scanner) {
        // Реализация изменения информации о животном
    }

    private static void saveAnimals() {
        // Реализация сохранения животных в файл
    }
}
