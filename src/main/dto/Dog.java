package main.dto;

public class Dog extends Animal {
    public Dog() {
        super();
    }

    public Dog(String name, int age, double weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.println("гав");
    }
}