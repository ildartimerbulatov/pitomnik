import java.util.List;

public class Animal {
    private int id;
    private String name;
    private String birthDate;
    private List<String> commands;
    private String type;

    public Animal(int id, String name, String birthDate, List<String> commands, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public String getType() {
        return type;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s", id, name, birthDate, String.join(", ", commands), type);
    }
}
