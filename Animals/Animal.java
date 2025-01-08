import java.util.List;

public class Animal {
    private int inventoryNumber;
    private String species;
    private String birthDate;
    private List<String> commands;
    private String nickname;
    private String purpose;

    public Animal(int inventoryNumber, String species, String birthDate, List<String> commands, String nickname, String purpose) {
        this.inventoryNumber = inventoryNumber;
        this.species = species;
        this.birthDate = birthDate;
        this.commands = commands;
        this.nickname = nickname;
        this.purpose = purpose;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return inventoryNumber + "," + species + "," + birthDate + ",\"" + String.join(", ", commands) + "\"," + nickname + "," + purpose;
    }
}