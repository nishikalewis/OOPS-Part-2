package edu.umb.cs681.hw01;

import java.util.*;
import static java.util.stream.Collectors.*;

public class Car {
    private int mileage, year;
    private float price;
    private String model, make;

    private int domCount;

    public Car(String make,String model, int year,int mileage, float price) {
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.model = model;
        this.make = make;
    }
    public int getMileage(){
        return this.mileage;
    }
    public int getYear(){
        return this.year;
    }

    public float getPrice(){
        return this.price;
    }
    public String getModel(){
        return this.model;
    }
    public String getMake(){
        return this.make;
    }
    public int getDominationCount(){
        return this.domCount;
    }

    public void setDominationCount(List<Car> usedCars) {
        int ct=0;
        int a=this.getMileage();
        float b=this.getPrice();
        int d=this.getYear();
        for(Car cr:usedCars){
            int x=cr.getMileage();
            float y=cr.getPrice();
            int z=cr.getYear();
            if(x<=a && y<=b && d>=z){
                if(x<a || y<b || d>z){
                    ct++;
                }
            }
        }
        this.domCount = ct;
    }
    public static void mileageSort(List<Car> usedCars) {
        List<Car> highMileage = usedCars.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .filter(car -> car.getMileage() > 25)
                .collect(toList());
        List<Car> lowMileage = usedCars.stream()
                .sorted(Comparator.comparing(Car::getMileage))
                .filter(car -> car.getMileage() <= 25)
                .collect(toList());
//
        OptionalInt maxhighMileage = highMileage.stream()
                .mapToInt(Car::getMileage)
                .max();


        OptionalInt minhighMileage = highMileage.stream()
                .mapToInt(Car::getMileage)
                .min();

        double avghighMileage = highMileage.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0.0); // Handles empty stream

        System.out.println("Maximum high mileage: " + maxhighMileage);
        System.out.println("Minimum high mileage: " + minhighMileage);
        System.out.println("Average high mileage: " + avghighMileage);
        System.out.println("No. of high Mileage cars for the given threshold: " + highMileage.stream().count());

        OptionalInt maxlowMileage = lowMileage.stream()
                .mapToInt(Car::getMileage)
                .max();

        OptionalInt minlowMileage = lowMileage.stream()
                .mapToInt(Car::getMileage)
                .min();

        double avglowMileage = lowMileage.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0.0); // Handles empty stream

        System.out.println("Maximum low mileage: " + maxlowMileage);
        System.out.println("Minimum low mileage: " + minlowMileage);
        System.out.println("Average low mileage: " + avglowMileage);
        System.out.println("No. of low Mileage cars for the given threshold: "+ lowMileage.stream().count());




    }
    public static void yearSort(List<Car> usedCars) {
        List<Car> latest = usedCars.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .filter(car -> car.getYear() >= 2020)
                .collect(toList());

        List<Car> old = usedCars.stream()
                .sorted(Comparator.comparing(Car::getYear))
                .filter(car -> car.getYear() < 2020)
                .collect(toList());


        OptionalInt maxhighYear = latest.stream()
                .mapToInt(Car::getYear)
                .max();


        OptionalInt minhighYear = latest.stream()
                .mapToInt(Car::getYear)
                .min();


        double avghighYear = latest.stream()
                .mapToInt(Car::getYear)
                .average()
                .orElse(0.0); // Handles empty stream

        System.out.println("Maximum high year: " + maxhighYear);
        System.out.println("Minimum high year: " + minhighYear);
        System.out.println("Average high year: " + avghighYear);
        System.out.println("No. of latest cars according to the threshold: "+latest.stream().count());

        OptionalInt maxlowYear = old.stream()
                .mapToInt(Car::getYear)
                .max();

        OptionalInt minlowYear = old.stream()
                .mapToInt(Car::getYear)
                .min();

        double avglowYear = old.stream()
                .mapToInt(Car::getYear)
                .average()
                .orElse(0.0); // Handles empty stream

        System.out.println("Maximum low year: " + maxlowYear);
        System.out.println("Minimum low year: " + minlowYear);
        System.out.println("Average low year: " + avglowYear);
        System.out.println("No. of old cars according to the threshold: " + old.stream().count());


    }
    public static void priceSort(List<Car> usedCars) {
        List<Car> highPrice = usedCars.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .filter(car -> car.getPrice() >= 15000)
                .collect(toList());

        List<Car> lowPrice = usedCars.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .filter(car -> car.getPrice() < 15000)
                .collect(toList());

        OptionalDouble maxhighPrice = highPrice.stream()
                .mapToDouble(Car::getPrice)
                .max();


        OptionalDouble minhighPrice = highPrice.stream()
                .mapToDouble(Car::getPrice)
                .min();


        double avghighPrice = highPrice.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0); // Handle empty stream

        System.out.println("Maximum high Price: " + maxhighPrice);
        System.out.println("Minimum high Price: " + minhighPrice);
        System.out.println("Average high Price: " + avghighPrice);
        System.out.println("No. of high priced cars according to the threshold: "+highPrice.stream().count());

        OptionalDouble maxlowPrice = lowPrice.stream()
                .mapToDouble(Car::getPrice)
                .max();

        OptionalDouble minlowPrice = lowPrice.stream()
                .mapToDouble(Car::getPrice)
                .min();

        double avglowPrice = lowPrice.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0); // Handle empty stream

        System.out.println("Maximum low Price: " + maxlowPrice);
        System.out.println("Minimum low Price: " + minlowPrice);
        System.out.println("Average low Price: " + avglowPrice);
        System.out.println("No. of high priced cars according to the threshold: " + lowPrice.stream().count());


    }

    public static void dominationcountSort(List<Car> usedCars) {
        for (Car car : usedCars) {
            car.setDominationCount(usedCars);
        }

        List<Car> highDomination = usedCars.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .filter(car -> car.getDominationCount() >= 2)
                .collect(toList());


        List<Car> lowDomination = usedCars.stream()
                .sorted(Comparator.comparing(Car::getDominationCount))
                .filter(car -> car.getDominationCount() < 2)
                .collect(toList());

        OptionalInt maxhighDomination = highDomination.stream()
                .mapToInt(Car::getDominationCount)
                .max();


        OptionalInt minhighDomination = highDomination.stream()
                .mapToInt(Car::getDominationCount)
                .min();


        double avghighDomination = highDomination.stream()
                .mapToInt(Car::getDominationCount)
                .average()
                .orElse(0.0); // Handle empty stream

        System.out.println("Maximum high Domination: " + maxhighDomination);
        System.out.println("Minimum high Domination: " + minhighDomination);
        System.out.println("Average high Domination: " + avghighDomination);
        System.out.println("No. of High Domination value cars according to the threshold: " +highDomination.stream().count());

        OptionalInt maxlowDomination = lowDomination.stream()
                .mapToInt(Car::getDominationCount)
                .max();

        OptionalInt minlowDomination = lowDomination.stream()
                .mapToInt(Car::getDominationCount)
                .min();

        double avglowDomination = lowDomination.stream()
                .mapToInt(Car::getDominationCount)
                .average()
                .orElse(0.0); // Handle empty stream

        System.out.println("Maximum low Domination: " + maxlowDomination);
        System.out.println("Minimum low Domination: " + minlowDomination);
        System.out.println("Average low Domination: " + avglowDomination);
        System.out.println("No. of Low Domination value cars according to the threshold: "+lowDomination.stream().count());



    }

    public static void main(String[] args) {
        List<Car> usedCars=new ArrayList<>();
        Car c1=new Car("volkswagen","hatchbag",2020,25,13000);
        usedCars.add(c1);
        Car c2=new Car("polo","sedan",2018,20,15000);
        usedCars.add(c2);
        Car c3=new Car("kia","suv",2022,30,25000);
        usedCars.add(c3);
        Car c4=new Car("rangerover","xuv",2023,35,50000);
        usedCars.add(c4);

        mileageSort(usedCars);
        yearSort(usedCars);
        priceSort(usedCars);
        dominationcountSort(usedCars);




    }


}

