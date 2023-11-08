package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.LinkedList;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LinkedList<Dose> doses;

    public Person(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doses = new LinkedList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LinkedList<Dose> getDoses() {
        return doses;
    }

    public void addDose(Dose dose) {
        doses.add(dose);
    }

    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }

    public AgeCat getAgeCat() {
        int age = getAge();
        if (age < 18) {
            return AgeCat.YOUNG;
        } else if (age < 50) {
            return AgeCat.MID;
        } else {
            return AgeCat.OLD;
        }
    }

    public int getVacCount() {
        return doses.size();
    }

}


