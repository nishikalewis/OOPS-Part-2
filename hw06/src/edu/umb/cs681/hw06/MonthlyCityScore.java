package edu.umb.cs681.hw06;

import java.util.List;
import static java.util.stream.Collectors.*;

public class MonthlyCityScore implements Runnable {
    List<List<String>> matrix= new edu.umb.cs681.hw06.CityScore().trimlist();
    public static List<Double> AnalyzeData(List<List<String>> matrix) {
        List<Double> cityScrMonthValues = matrix.stream()
                .map(row -> {
                    try {
                        return Double.parseDouble(row.get(4));
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }

                })
                .collect(toList());
        return cityScrMonthValues;
    }

    @Override
    public void run() {
        List<Double> cityScrMonthValues = AnalyzeData(matrix);


        double sumofMonthlyAgg = cityScrMonthValues.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Sum of Monthly Aggregate city score values: " + sumofMonthlyAgg);

        double avgofMonthlyAgg = sumofMonthlyAgg / cityScrMonthValues.size();
        System.out.println("Average of Monthly Aggregate city score values: " + avgofMonthlyAgg );

    }
}