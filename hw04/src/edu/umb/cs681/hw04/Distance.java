package edu.umb.cs681.hw04;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.*;
import java.util.stream.IntStream;

public class Distance {
    public static double get(List<Double> p1, List<Double> p2) {
        return Distance.get(p1, p2, new Euclidean());
    }

    public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
        return metric.distance(p1, p2);
    }

    public static List<List<Double>> matrix(List<List<Double>> points) {
        return Distance.matrix(points, new Euclidean());
    };

    public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {


        int numOfPoints = points.size();

        List<List<Double>> distanceMatrix = IntStream.range(0, numOfPoints)
                .mapToObj(i -> points.stream()
                        .map(j -> get(points.get(i), j, metric))
                        .collect(toList()))
                .collect(toList());


        return distanceMatrix;
    }

    private static List<List<Double>> initDistanceMatrix(int numOfPoints){
        List<List<Double>> distanceMatrix = new ArrayList<>(numOfPoints);
        for(int i=0; i < numOfPoints; i++) {
            Double[] vector = new Double[numOfPoints];
            Arrays.fill(vector, 0.0);
            distanceMatrix.add( Arrays.asList(vector) );
        }
        return distanceMatrix;
    }

    public static void main(String args[]){

        int numofpoints = 1000;
        int numofdimensions = 100;


        List<List<Double> > points = randomPointsGen(numofpoints, numofdimensions);

        List<Double> point1 = points.get(0);
        List<Double> point2 = points.get(1);


        double euclidean = Distance.get(point1, point2);
        System.out.println("Euclidean Distance: " + euclidean);


        double manhattan = Distance.get(point1, point2, new Manhattan());
        System.out.println("Manhattan Distance: " + manhattan);
        List<List<Double>> a1= randomPointsGen(1000,100);
        List<List<Double>> b1= randomPointsGen(1000,100);

        System.out.println("Testing euclidean distance at point 50- "+Distance.get(a1.get(50),b1.get(50)));
        System.out.println("Testing manhattan distance at point 50- "+Distance.get(a1.get(50),b1.get(50),new Manhattan()));

    }



    private static List<List<Double> > randomPointsGen(int numofpoints, int numofdimensions) {
        Random random = new Random();
        List<List<Double> > points = new ArrayList<>();

        for (int i = 0; i < numofpoints; i++) {
            List<Double> point = new ArrayList<>(numofdimensions);
            for (int j = 0; j < numofdimensions; j++) {
                point.add(random.nextDouble());
            }
            points.add(point);
        }

        return points;
    }
}


