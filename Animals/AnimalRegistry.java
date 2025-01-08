import java.util.*;

public class AnimalRegistry {
    private List<Animal> animals;
    private int animalCounter;

    public AnimalRegistry() {
        this.animals = FileHandler.loadAnimals();
        this.animalCounter = animals.size();
    }

    public void addAnimal(String species, String birthDate, List<String> commands, String nickname, String purpose) {
        int inventoryNumber = ++animalCounter;
        animals.add(new Animal(inventoryNumber, species, birthDate, commands, nickname, purpose));
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
        return animalCounter;
    }

    public void saveData() {
        FileHandler.saveAnimals(animals);
    }
}