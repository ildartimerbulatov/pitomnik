import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class Animal {
    int inventoryNumber;
    String species;
    Date birthDate;
    List<String> commands;
    String nickname;
    String purpose;

    // Конструктор
    public Animal(int inventoryNumber, String species, Date birthDate, List<String> commands, String nickname, String purpose) {
        this.inventoryNumber = inventoryNumber;
        this.species = species;
        this.birthDate = birthDate;
        this.commands = commands;
        this.nickname = nickname;
        this.purpose = purpose;
    }

    // Геттер для inventoryNumber
    public int getInventoryNumber() {
        return inventoryNumber;
    }

    // Геттер для birthDate
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return inventoryNumber + "," + species + "," + sdf.format(birthDate) + ",\"" + String.join(", ", commands) + "\"," + nickname + "," + purpose;
    }
}