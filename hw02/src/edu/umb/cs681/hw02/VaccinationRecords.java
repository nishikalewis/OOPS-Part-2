package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class VaccinationRecords {

    public VaccinationRecords() {

    }

    private static LinkedList<Person> generateRandomPeople(int count) {
        LinkedList<Person> people = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String firstName = "First" + random.nextInt(count);
            String lastName = "Last" + random.nextInt(count);
            LocalDate dob = LocalDate.of(1960 + i % 50, 1 + (i % 12), 1 + (i % 28));
            people.add(new Person(firstName, lastName, dob));
        }

        return people;
    }

    private static List<Dose> generateRandomDosage(int count) {
        List<Dose> dosage = new ArrayList<>();
        Random random = new Random();

        String[] vaccineNames = {
                "MP126", "RR234", "RL977", "FY687", "YL667",
                "SU876", "NS286", "SN257", "WN252", "NQ637",
                "VH768", "KJ899", "MD629", "HY764", "MK703",
                "NL892", "JU829", "LD356", "YN925", "BJ728"
        };

        for (int i = 0; i < count; i++) {
            String vacProductName = vaccineNames[random.nextInt(vaccineNames.length)];
            String lotNumber = "LotNo." + random.nextInt(count);
            LocalDate date = LocalDate.of(2023, 1 + (i % 12), 1 + (i % 28));
            String vacSite = "Site" + random.nextInt(count);
            dosage.add(new Dose(vacProductName, lotNumber, date, vacSite));
        }

        return dosage;
    }

    private static LinkedList<Person> VaccinationData(LinkedList<Person> persons, List<Dose> doses) {
        LinkedList<Person> personDosePairs = new LinkedList<>();
        Random random = new Random();

        for (Person person : persons) {
            int numDoses = random.nextInt(5);
            for (int i = 0; i < numDoses; i++) {
                Dose dose = doses.get(random.nextInt(doses.size()));
                person.getDoses().add(dose);


            }
        }

        return persons;
    }

    public static void main(String[] args) {


        LinkedList<Person> people = VaccinationData(generateRandomPeople(1000), generateRandomDosage(1000));



        Map<AgeCat, Long> ageCatDiv = people.stream()
                .collect(groupingBy(Person::getAgeCat, counting()));

        System.out.println("Vaccination Rates based on Age Category:");
        ageCatDiv.forEach((ageCat, count) -> {
            long fullyVacByAge = people.stream()
                    .filter(p -> p.getAgeCat() == ageCat && p.getAge() >= 16 && p.getVacCount() >= 3)
                    .count();

            float vacRateByAge = (float) fullyVacByAge / count * 100;
            System.out.println(ageCat + " - Vaccination Rate for this age category: " + vacRateByAge + "%");
        });


        Map<AgeCat, List<Person>> agebasedGroups = people.stream()
                .collect(groupingBy(Person::getAgeCat));

        System.out.println("Average Number of Vaccinations Administered based on each Age Category:");

        agebasedGroups.forEach((ageCat, peopleInCategory) -> {
            double avgVacCountInAgeGroup = peopleInCategory.stream()
                    .filter(p -> p.getAge() >= 16 && p.getVacCount() >= 3)
                    .mapToInt(Person::getVacCount)
                    .average()
                    .orElse(0);
            System.out.println(ageCat + " - Average Vaccination Count for this age category: " + avgVacCountInAgeGroup);
        });


        Map<Boolean, Double> averageAgeUnvaccinated = people.stream()
                .collect(Collectors.partitioningBy(
                        person -> person.getVacCount() == 0,
                        Collectors.averagingDouble(person -> person.getAge())
                ));

        System.out.println("Unvaccinated vs. vaccinated people's average age:");
        System.out.println("UNVACCINATED: " + averageAgeUnvaccinated.get(true));
        System.out.println("VACCINATED: " + averageAgeUnvaccinated.get(false));



    }

}







