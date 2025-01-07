public class Animal {
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

    public void addCommand(String newCommand) {
        if (!commands.contains(newCommand)) {
            commands += ", " + newCommand;
        }
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + birthDate + ", " + commands + ", " + type;
    }
}
