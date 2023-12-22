package edu.umb.cs681.hw18;

import java.util.List;
import static java.util.stream.Collectors.*;

public class MonthlyCityScore {
    public static List<Double> AnalyzeData(List<List<String>> matrix) {
        List<Double> cityScrMonthValues = matrix.stream().parallel()

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

}