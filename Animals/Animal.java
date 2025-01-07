import java.util.*;

public class Animal {
    private String id;
    private String name;
    private String birthDate;
    private List<String> commands;
    private String type;

    // Конструктор класса
    public Animal(String id, String name, String birthDate, String commands, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>(Arrays.asList(commands.split(",")));  // Список команд
        this.type = type;
    }

    // Геттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getType() {
        return type;
    }

    // Метод для добавления новой команды
    public void addCommand(String command) {
        if (command != null && !command.trim().isEmpty()) {
            commands.add(command.trim());  // Добавляем команду в список
        }
    }

    // Переопределение метода toString для корректной записи в файл
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", id, name, birthDate, String.join(", ", commands), type);
    }
}
