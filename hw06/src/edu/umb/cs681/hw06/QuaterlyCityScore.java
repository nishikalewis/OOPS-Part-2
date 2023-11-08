package edu.umb.cs681.hw06;
import java.util.List;
public class QuaterlyCityScore implements Runnable{
    List<List<String>> matrix= new CityScore().trimlist();

    public static double calculateQuaterlyAverage(List<List<String>> matrix) {
        return matrix.stream()
                .skip(1) // Skip the header row
                .map(row -> Double.parseDouble(row.get(5))) // Convert the CTY_SCR_QUARTER_AGG value to a double
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0); // Return 0.0 if the list is empty
    }

    public static double calculateQuaterlyMin(List<List<String>> matrix) {
        return matrix.stream()
                .skip(1) // Skip the header row
                .map(row -> Double.parseDouble(row.get(5))) // Convert the CTY_SCR_QUARTER_AGG value to a double
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0); // Return 0.0 if the list is empty
    }

    public static double calculateQuaterlyMax(List<List<String>> matrix) {
        return matrix.stream()
                .skip(1) // Skip the header row
                .map(row -> Double.parseDouble(row.get(5))) // Convert the CTY_SCR_QUARTER_AGG value to a double
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0); // Return 0.0 if the list is empty
    }


    @Override
    public void run() {


        double quaterlyAverage = calculateQuaterlyAverage(matrix);
        System.out.println("Average of Quaterly City Score: " + quaterlyAverage );
        double quaterlyMin = calculateQuaterlyMin(matrix);
        System.out.println("Minimum of Quaterly City Score: " + quaterlyMin);
        double quaterlyMax = calculateQuaterlyMax(matrix);
        System.out.println("Maximum Quaterly City Score: " + quaterlyMax);

    }
}
