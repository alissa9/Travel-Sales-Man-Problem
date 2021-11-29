import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* A program to solve the travel salesman problem,This program work by permutation Algorithm
to get the shortest distance from the paths generated*/

public class Main {
    /* to pass in the file for the program by uncommenting the required one */
//    private static final String FILENAME = "test1tsp.txt";
//    private static final String FILENAME = "test2atsp.txt";
//    private final String FILENAME = "test3atsp.txt";
    private static final String FILENAME = "test1-20.txt";
//    private final String FILENAME = "test2-20.txt";
//    private final String FILENAME = "test3-20.txt";

    public static void main(String[] args) {

        List<Double> distances = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        FileManager manager = new FileManager(new File(FILENAME));
        manager.readFileData();
        manager.populateCities();

        permute(manager.getCities(), 0,manager.getNodes(),distances,routes);
        int index = distances.indexOf(Collections.min(distances));


        long endTime = System.currentTimeMillis() - startTime;


        printResults(distances, routes, index,manager.getFile(), endTime);


    }


    private static void printResults(List<Double> distances, List<String> routes, int index, File file, long endTime) {
        System.out.println("Test file name -> " + file.getName());
        System.out.println("Shortest Route -> " + routes.get(index));
        System.out.println("Distance  -> " + (Collections.min(distances)));
        System.out.println("Time taken  -> " + endTime + "  ms");

    }




    public static void permute(int[] route, int start, List<CityNode> nodes, List<Double> distances, List<String> routes) {

        double routDistance = 0;
        int startNode = route[0];


        for(int i = start; i < route.length; i++){
            int temp = route[start];
            route[start] = route[i];
            route[i] = temp;
            permute(route, start + 1,nodes,distances,routes);
            route[i] = route[start];
            route[start] = temp;

        }
        if (start == route.length - 1 && route[0] == startNode) {
            int currentNode ;
            int nextNode;



            for (int i = 0; i < route.length; i++) {

                if(i == route.length - 1){
                    currentNode = route[i];
                    nextNode = route[0];
                }else{
                    currentNode = route[i];
                    nextNode = route[i + 1];

                }

                routDistance = calcDistance(nodes, routDistance, currentNode, nextNode);


            }

            int[] currentRoute = insertCityPos(route, startNode);


            distances.add(routDistance);
            routes.add(Arrays.toString(currentRoute));

        }

    }

    private static int[] insertCityPos(int[] route, int startNode) {
        int[] currentRoute = new int[route.length + 1];

        for (int i = 0; i < route.length + 1; i++){
            if(i == route.length) currentRoute[i] = startNode;
            else currentRoute[i] = route[i];
        }
        return currentRoute;
    }


    private static double calcDistance(List<CityNode> nodes, double routDistance, int currentNode, int nextNode) {
        int currXCoord = (nodes.get(currentNode - 1).getX());
        int currYCoord = (nodes.get(currentNode - 1).getY());

        int nextXCoord = (nodes.get(nextNode - 1).getX());
        int nextYCoord = (nodes.get(nextNode - 1).getY());

        routDistance = routDistance
                +  Math.sqrt((nextXCoord-currXCoord)*(nextXCoord-currXCoord)
                + (nextYCoord-currYCoord)*(nextYCoord-currYCoord));
        return routDistance;
    }

}

