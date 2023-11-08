package edu.umb.cs681.hw06;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

public class CityScore {
    public CityScore(){
    }

    public List<List<String>> trimlist() {
        Path path = Paths.get("rpt_city_score_agg_v.csv");

        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> matrix = lines
                    .limit(846)
                    .map(line -> {
                        String[] values = line.split(",");
                        return Stream.of(values)
                                .limit(6)
                                .collect(toList());
                    })
                    .collect(toList());
            return matrix;
        } catch (IOException ex) {
            System.out.println("IO Exception Occured");
        }
        return null;
    }

    public static void main(String[] args) {


        Thread thread1=new Thread(new QuaterlyCityScore());
        Thread thread2=new Thread(new MonthlyCityScore());
        Thread thread3=new Thread(new WeeklyCityScore());
        thread1.start();
        thread2.start();
        thread3.start();
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }catch ( InterruptedException e){
            e.printStackTrace();

        }

    }
}





