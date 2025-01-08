import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class AnimalRegistry {
    private List<Animal> animals;
    private int animalCounter;

    public AnimalRegistry() {
        this.animals = FileHandler.loadAnimals();
        this.animalCounter = animals.stream()
                                    .mapToInt(Animal::getInventoryNumber)
                                    .max()
                                    .orElse(0);
    }

    public void addAnimal(String species, String birthDate, List<String> commands, String nickname, String purpose) {
        int inventoryNumber = ++animalCounter;
        animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
        FileHandler.saveAnimals(animals); // Сохраняем изменения в файл
    }

    public void deleteAnimal(int inventoryNumber) {
        boolean removed = animals.removeIf(animal -> animal.getInventoryNumber() == inventoryNumber);
        if (removed) {
            System.out.println("Запись удалена.");
            FileHandler.saveAnimals(animals); // Сохраняем изменения в файл
        } else {
            System.out.println("Запись с inventory_number = " + inventoryNumber + " не найдена.");
        }
    }

    public void printAnimalsByBirthDate() {
        animals.sort(Comparator.comparing(animal -> LocalDate.parse(animal.getBirthDate())));
        animals.forEach(System.out::println);
    }

    public void printAllAnimals() {
        animals.forEach(System.out::println);
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public void saveData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }
}