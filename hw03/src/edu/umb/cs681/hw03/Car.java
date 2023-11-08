package edu.umb.cs681.hw03;
import java.util.*;

public class Car {
    private int mileage, year;
    private float price;
    private String model, make;

    private int domCount;

    public Car(String make, String model, int year, int mileage, float price) {
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.model = model;
        this.make = make;
    }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }

    public String getModel() {
        return this.model;
    }

    public String getMake() {
        return this.make;
    }

    public int getDominationCount() {
        return this.domCount;
    }

    public void setDominationCount(List<Car> usedCars) {
        int ct = 0;
        int a = this.getMileage();
        float b = this.getPrice();
        int d = this.getYear();
        for (Car cr : usedCars) {
            int x = cr.getMileage();
            float y = cr.getPrice();
            int z = cr.getYear();
            if (x <= a && y <= b && d >= z) {
                if (x < a || y < b || d > z) {
                    ct++;
                }
            }
        }
        this.domCount = ct;
    }

    public static void main(String[] args) {
        List<Car> usedCars = new ArrayList<>();
        Car c1 = new Car("volkswagen", "hatchbag", 2020, 25, 13000);
        usedCars.add(c1);
        Car c2 = new Car("polo", "sedan", 2018, 20, 15000);
        usedCars.add(c2);
        Car c3 = new Car("kia", "suv", 2022, 30, 25000);
        usedCars.add(c3);
        Car c4 = new Car("rangerover", "xuv", 2023, 35, 50000);
        usedCars.add(c4);
        double averagePrice = usedCars.stream()
                .map(car -> car.getPrice())
                .reduce(new CarPriceResultHolder(),
                        (result, price) -> {
                            result.accumulate(price);
                            return result;
                        },
                        (finalResult, intermediateResult) -> {
                            finalResult.combine(intermediateResult);
                            return finalResult;
                        })
                .getAverage();
        System.out.println("Average Price= " + averagePrice);

    }
}




