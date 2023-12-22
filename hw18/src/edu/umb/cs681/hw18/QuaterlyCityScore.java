package edu.umb.cs681.hw18;
import java.util.List;
public class QuaterlyCityScore{
    public static double calculateQuaterlyAverage(List<List<String>> matrix) {
        return matrix.stream().parallel()
                .skip(1)
                .map(row -> Double.parseDouble(row.get(5)))
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public static double calculateQuaterlyMin(List<List<String>> matrix) {
        return matrix.stream().parallel()
                .skip(1)
                .map(row -> Double.parseDouble(row.get(5)))
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);
    }

    public static double calculateQuaterlyMax(List<List<String>> matrix) {
        return matrix.stream().parallel()
                .skip(1)
                .map(row -> Double.parseDouble(row.get(5)))
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }


}

