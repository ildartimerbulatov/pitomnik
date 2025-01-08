import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AnimalRegistry {
    private List<Animal> animals;
    private int animalCounter;

    public AnimalRegistry() {
        this.animals = loadAnimalsFromCSV();
        this.animalCounter = animals.size();
    }

    public void addAnimal(String species, String birthDate, List<String> commands, String nickname, String purpose) {
        int inventoryNumber = ++animalCounter;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
            animals.add(new Animal(inventoryNumber, species, date, commands, nickname, purpose));
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Используйте формат гггг-мм-дд.");
        }
    }

    public void deleteAnimal(int inventoryNumber) {
        animals.removeIf(animal -> animal.getInventoryNumber() == inventoryNumber);
    }

    public void printAnimalsByBirthDate() {
        animals.sort(Comparator.comparing(Animal::getBirthDate));
        animals.forEach(System.out::println);
    }

    public void printAllAnimals() {
        animals.forEach(System.out::println);
    }

    public int getAnimalCount() {
        return animals.size();
    }

    private List<Animal> loadAnimalsFromCSV() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Human_Friends.csv"))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int inventoryNumber = Integer.parseInt(data[0]);
                String species = data[1];
                Date birthDate = sdf.parse(data[2]);
                List<String> commands = List.of(data[3].replace("\"", "").split(", "));
                String nickname = data[4];
                String purpose = data[5];

                animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
            }
        } catch (IOException | ParseException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return animals;
    }

    public void saveAnimalsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Human_Friends.csv"))) {
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}