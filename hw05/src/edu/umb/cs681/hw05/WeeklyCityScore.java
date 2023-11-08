package edu.umb.cs681.hw05;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeeklyCityScore {
    public Map<Boolean, List<String>> cityScoreWeekly(List<List<String>> matrix) {
        double threshold = 1.2;

        // Skip the header row
        List<String> weeklyData = matrix.stream()
                .skip(1)
                .map(row -> row.get(3))
                .collect(Collectors.toList());
        return weeklyData.stream()
                .collect(Collectors.partitioningBy(value -> {
                    try {
                        double numericValue = Double.parseDouble(value);
                        return numericValue > threshold;
                    } catch (NumberFormatException e) {

                        return false;
                    }
                }));
    }
}

