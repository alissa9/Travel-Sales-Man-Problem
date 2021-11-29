import java.io.File;
import java.util.*;

/* A program to solve the travel salesman problem,This program work by permutation Algorithm
to get the shortest distance from the paths generated*/

public class Main {
    /* to pass in the file for the program by uncommenting the required one */

    private static final String FILENAME = "test1tsp.txt";
//    private static final String FILENAME = "test2atsp.txt";
//    private static  final String FILENAME = "test3atsp.txt";
//    private static final String FILENAME = "test1-20.txt";
//    private static final String FILENAME = "test2-20.txt";
//    private static final String FILENAME = "test3-20.txt";

    public static void main(String[] args) {

        List<Double> distances = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        /* Initialize file manager with the test files */
        FileManager fileManager = new FileManager(new File(FILENAME));
        /* Reading file data into a node object */
        fileManager.readFileData();
        /* Populating an array for cities */
        fileManager.populateCities();
        permute(fileManager.getCities(), 0, fileManager.getNodes(), distances, routes);
        /* Getting the index of the shortest distance */
        int index = distances.indexOf(Collections.min(distances));

        long endTime = System.currentTimeMillis() - startTime;

        printResults(distances, routes, index, fileManager.getFile(), endTime);


    }

    /* Printing the results */
    private static void printResults(List<Double> distances, List<String> routes, int index, File file, long endTime) {
        System.out.println("Test file name -> " + file.getName());
        System.out.println("Shortest Route -> " + routes.get(index));
        System.out.println("Distance  -> " + (Collections.min(distances)));
        System.out.println("Time taken  -> " + endTime + "ms");
    }


    /* Permutation : creating all possible combination for the given route */
    public static void permute(int[] route, int start, List<CityNode> nodes, List<Double> distances, List<String> routes) {

        double routDistance = 0;
        int startNode = route[0];

        /* Creating the combination between the cities */
        for (int i = start; i < route.length; i++) {
            int temp = route[start];
            route[start] = route[i];
            route[i] = temp;
            permute(route, start + 1, nodes, distances, routes);
            route[i] = route[start];
            route[start] = temp;

        }
        /* Checking if reached the last node(city) and the first node in the route is the starting node */
        if (start == route.length - 1 && route[0] == startNode) {
            /* calculating teh distance for the current route */
            routDistance = getRoutDistance(route, nodes, routDistance);
            /* adding each node into an array for the cities */
            int[] currentRoute = insertCityPos(route, startNode);
            /* Storing the current route distance */
            distances.add(routDistance);
            /* storing the current route */
            routes.add(Arrays.toString(currentRoute));

        }

    }

    //    calculating distance between all the cities (route distance)
    private static double getRoutDistance(int[] route, List<CityNode> nodes, double routDistance) {
        int currentNode;
        int nextNode;
        for (int i = 0; i < route.length; i++) {

            if (i == route.length - 1) {
                currentNode = route[i];
                nextNode = route[0];
            } else {
                currentNode = route[i];
                nextNode = route[i + 1];

            }
            routDistance = euclidean(nodes, routDistance, currentNode, nextNode);


        }
        return routDistance;
    }

    /* storing the current route node into an array */
    private static int[] insertCityPos(int[] route, int startNode) {
        int[] currentRoute = new int[route.length + 1];

        for (int i = 0; i < route.length + 1; i++) {
            if (i == route.length) currentRoute[i] = startNode;
            else currentRoute[i] = route[i];
        }
        return currentRoute;
    }


    /* Calculating the distance between each 2 cities using euclidean formula */
    private static double euclidean(List<CityNode> nodes, double routDistance, int currentNode, int nextNode) {
        int currXCord = (nodes.get(currentNode - 1).getX());
        int currYCord = (nodes.get(currentNode - 1).getY());

        int nextXCord = (nodes.get(nextNode - 1).getX());
        int nextYCord = (nodes.get(nextNode - 1).getY());

        routDistance = routDistance
                + Math.sqrt((nextXCord - currXCord) * (nextXCord - currXCord)
                + (nextYCord - currYCord) * (nextYCord - currYCord));
        return routDistance;
    }

}

