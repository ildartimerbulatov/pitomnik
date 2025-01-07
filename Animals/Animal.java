public class Animal {
    private int id;
    private String name;
    private String birthDate;
    private String commands;
    private String species;

    public Animal(int id, String name, String birthDate, String commands, String species) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
        this.species = species;
    }

    public int getId() {
        return id;
    }

    public void addCommand(String newCommand) {
        this.commands += ", " + newCommand;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + birthDate + ", " + commands + ", " + species;
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}
