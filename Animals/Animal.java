import java.time.LocalDate;

public class Animal {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String commands;
    private String type;

    public Animal(String id, String name, LocalDate birthDate, String commands, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + birthDate + "," + commands + "," + type;
    }
}