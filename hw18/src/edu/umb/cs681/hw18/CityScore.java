package edu.umb.cs681.hw18;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.stream.Stream;

public class CityScore {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("rpt_city_score_agg_v.csv");

        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> matrix = lines.parallel()
                    .limit(846)
                    .map(line -> {
                        String[] values = line.split(",");
                        return Stream.of(values)
                                .limit(6)
                                .collect(toList());
                    })
                    .collect(toList());
            double quaterlyAverage = QuaterlyCityScore.calculateQuaterlyAverage(matrix);
            System.out.println("Average of Quaterly City Score: " + quaterlyAverage );


            double quaterlyMin = QuaterlyCityScore.calculateQuaterlyMin(matrix);
            System.out.println("Minimum of Quaterly City Score: " + quaterlyMin);


            double quaterlyMax = QuaterlyCityScore.calculateQuaterlyMax(matrix);
            System.out.println("Maximum Quaterly City Score: " + quaterlyMax);


            List<Double> cityScrMonthValues = MonthlyCityScore.AnalyzeData(matrix);


            double sumofMonthlyAgg = cityScrMonthValues.stream().parallel().mapToDouble(Double::doubleValue).sum();
            System.out.println("Sum of Monthly Aggregate city score values: " + sumofMonthlyAgg);

            double avgofMonthlyAgg = sumofMonthlyAgg / cityScrMonthValues.size();
            System.out.println("Average of Monthly Aggregate city score values: " + avgofMonthlyAgg );



          WeeklyCityScore weeklyCityScore = new WeeklyCityScore();
            Map<Boolean, List<String>> partitionedData = weeklyCityScore.cityScoreWeekly(matrix);


            List<String> valuesAboveThreshold = partitionedData.get(true);
            List<String> valuesBelowThreshold = partitionedData.get(false);
            System.out.println("City Score Weekly Aggregate Values above threshold: " + valuesAboveThreshold);
            System.out.println("City Score Weekly Aggregate Values below threshold: " + valuesBelowThreshold);

        } catch (IOException ex) {
            System.out.println("IOException Occurred");
        }
    }
}






