import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ClassificationDiagramExample {

    public static void main(String[] args) {
        // Создаем dataset для диаграммы
        Map<String, Integer> speciesCount = new HashMap<>();

        // Читаем данные из файла
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/ildar/OneDrive/Desktop/pitomnik/Animals/Human_Friends.csv"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Пропускаем заголовок
                    continue;
                }

                String[] data = line.split(",");
                String species = data[1]; // Вид животного
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Выводим текстовую диаграмму классификации
        System.out.println("Текстовая диаграмма классификации:");
        System.out.println("Животные");
        System.out.println("├── Домашние животные");
        System.out.println("│   ├── Собаки: " + speciesCount.getOrDefault("Dog", 0));
        System.out.println("│   ├── Кошки: " + speciesCount.getOrDefault("Cat", 0));
        System.out.println("│   ├── Кролики: " + speciesCount.getOrDefault("Rabbit", 0));
        System.out.println("│   ├── Хомяки: " + speciesCount.getOrDefault("Hamster", 0));
        System.out.println("│   ├── Рыбы: " + speciesCount.getOrDefault("Fish", 0));
        System.out.println("│   ├── Попугаи: " + speciesCount.getOrDefault("Parrot", 0));
        System.out.println("│   ├── Черепахи: " + speciesCount.getOrDefault("Turtle", 0));
        System.out.println("│   ├── Морские свинки: " + speciesCount.getOrDefault("Guinea Pig", 0));
        System.out.println("│   ├── Хорьки: " + speciesCount.getOrDefault("Ferret", 0));
        System.out.println("│   └── Ящерицы: " + speciesCount.getOrDefault("Lizard", 0));
        System.out.println("└── Вьючные животные");
        System.out.println("    ├── Лошади: " + speciesCount.getOrDefault("Horse", 0));
        System.out.println("    ├── Ослы: " + speciesCount.getOrDefault("Donkey", 0));
        System.out.println("    ├── Мулы: " + speciesCount.getOrDefault("Mule", 0));
        System.out.println("    ├── Ламы: " + speciesCount.getOrDefault("Llama", 0));
        System.out.println("    ├── Альпаки: " + speciesCount.getOrDefault("Alpaca", 0));
        System.out.println("    ├── Яки: " + speciesCount.getOrDefault("Yak", 0));
        System.out.println("    └── Козлы: " + speciesCount.getOrDefault("Pack Goat", 0));
    }
}