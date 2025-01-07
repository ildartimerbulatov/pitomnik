import java.util.*;

public class Animal {
    private String id; // Изменили тип на String
    private String name;
    private String birthDate;
    private List<String> commands;
    private String type;

    public Animal(String id, String name, String birthDate, String commands, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>(Arrays.asList(commands.split(",")));
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public String getName() {
        return name;  // Исправлено
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Birth Date: %s, Commands: %s, Type: %s", id, name, birthDate, String.join(", ", commands), type);
    }
}
