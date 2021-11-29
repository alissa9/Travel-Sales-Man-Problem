import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CW {
    public static void main(String[] args) {
        File myObj = new File("test1tsp.txt");
        ArrayList<String> dataLine = new ArrayList<>();
        // reading file data into dataLine and returning the total num of cities
        int totalCities = readData(dataLine, myObj);
        // initialising dataset with total num of cities
        int[][] dataSet = new int[totalCities][3];
        populateDataset(dataSet, dataLine);
        // calculating distance
        double distance = getDistance(calculateDistance(dataSet));
        // outputting results
        displayResult(myObj, distance);
    }

    private static ArrayList<Double> calculateDistance(int[][] dataSet) {
        ArrayList<Double> distance = new ArrayList<>();
        try {
            for (int i = 0; i < dataSet.length; i++) {
                // current city
                int[] city1;
                // next city
                int[] city2;
                // check if city is last
                if (i == dataSet.length - 1) {
                    city1 = dataSet[0];
                    city2 = dataSet[i];
                } else {
                    // current city
                    city1 = dataSet[i];
                    city2 = dataSet[i + 1];
                }
                // current city coordinates
                int x1 = city1[1];
                int y1 = city1[2];
                // next city coordinates
                int x2 = city2[1];
                int y2 = city2[2];
                distance.add(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        System.out.println(distance);
        return distance;
    }

    private static double getDistance(ArrayList<Double> distances) {
        double total = 0;
        for (Double distance : distances) {
            total = total + distance;
        }
        return total;
    }


    private static int readData(ArrayList<String> dataLine, File myObj) {
        int totalCities = 0;
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dataLine.add(data);
                totalCities++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return totalCities;

    }

    private static void populateDataset(int[][] dataSet, ArrayList<String> dataLine) {
        for (int i = 0; i < dataLine.size(); i++) {
            String[] split = dataLine.get(i)
                    .trim()
                    .replaceAll("( )+", " ")
                    .split(" ");
            System.out.println(Arrays.toString(split));
            for (int j = 0; j < split.length; j++) {
                dataSet[i][j] = Integer.parseInt(split[j]);
            }
        }
    }

    private static void displayResult(File file, double distance) {
        System.out.println("Test File Name: " + file.getName());
        System.out.println("Distance: " + distance);
        System.out.println("Tour");
        System.out.println("Time");
    }

}

