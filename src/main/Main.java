package main;

import main.dto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            runMenu(scanner);
        }
    }

    private static void runMenu(Scanner scanner) {
        List<Animal> animals = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println();
            System.out.println("Введите команду: add/list/exit");

            Command command = Command.fromString(scanner.nextLine());

            if (command == null) {
                System.out.println("Неизвестная команда");
                continue;
            }

            switch (command) {
                case ADD:
                    Animal animal = createAnimal(scanner);
                    animals.add(animal);
                    System.out.println("Животное добавлено");
                    animal.say();
                    break;

                case LIST:
                    printAnimals(animals);
                    break;

                case EXIT:
                    isRunning = false;
                    System.out.println("Программа завершена");
                    break;
            }
        }
    }

    private static Animal createAnimal(Scanner scanner) {
        while (true) {
            System.out.println("Какое животное добавить? cat/dog/duck");
            String animalType = scanner.nextLine()
                    .trim()
                    .toLowerCase();
            if (!animalType.equals("cat")
                    && !animalType.equals("dog")
                    && !animalType.equals("duck")) {
                System.out.println("Неизвестный вид животного");
                continue;
            }
            String name = readString(scanner, "Введите имя:");
            int age = readInt(scanner, "Введите возраст:");
            while (age < 0) {
                System.out.println("Возраст не может быть отрицательным");
                age = readInt(scanner, "Введите возраст:");
            }
            double weight = readDouble(scanner, "Введите вес:");
            while (weight < 0) {
                System.out.println("Вес не может быть отрицательным");
                weight = readDouble(scanner, "Введите вес:");
            }
            String color = readString(scanner, "Введите цвет:");
            switch (animalType) {
                case "cat":
                    return new Cat(name, age, weight, color);
                case "dog":
                    return new Dog(name, age, weight, color);
                case "duck":
                    return new Duck(name, age, weight, color);
                default:
                    throw new IllegalStateException("Неизвестный вид животного");
            }
        }
    }

    private static void printAnimals(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("Список животных пуст");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static String readString(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Введите целое число");
            }
        }
    }

    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine()
                    .trim()
                    .replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Введите число");
            }
        }
    }
}
