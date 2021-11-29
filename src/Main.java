import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* A program to solve the travel salesman problem,This program work by permutation Algorithm
to get the shortest distance from the paths generated*/

public class Main {
    /* to pass in the file for the program by uncommenting the required one */
    private final String FILENAME = "test1tsp.txt";
//    private final String FILENAME = "test2atsp.txt";
//    private final String FILENAME = "test3atsp.txt";
//    private final String FILENAME = "test1-20.txt";
//    private final String FILENAME = "test2-20.txt";
//    private final String FILENAME = "test3-20.txt";

    public static void main(String[] args) {

        try {
            List<Node> nodes = readFileData(new File("test2atsp.txt"));
            int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8};
            List<Double> distances = new ArrayList<>();
            List<String> routes = new ArrayList<>();
            permute(intArr, 0, nodes, distances, routes);
            /* Getting the shortest distance from the permute function */
            int index = distances.indexOf(Collections.min(distances));
            System.out.println("File Name -> " );
            System.out.println("Route -> " + routes.get(index));
            System.out.println("Shortest Distance  -> " + (Collections.min(distances)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    // Reading the txt file then separating and storing the values
    private static List<Node> readFileData(File file) throws FileNotFoundException {
        List<Node> nodes = new ArrayList<>();
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String lines = myReader.nextLine();
            String[] rows = lines
                    .trim()
                    .replaceAll("( )+", " ")
                    .split(" ");
            /* the first row of the txt file which is the city number */
            int cityNum = Integer.parseInt(rows[0]);

            /* the second row of the txt file which is the x coordinates */
            int cityX = Integer.parseInt(rows[1]);

            /* the second row of the txt file which is the y coordinates */
            int cityY = Integer.parseInt(rows[2]);

            nodes.add(new Node(cityX, cityY, cityNum));
        }

        myReader.close();
        return nodes;
    }

    /*  The Permute method generates all possible routes then I calculate all routes
       after that I use it to get the shortest distance calculated */
    public static void permute(int[] route, int start, List<Node> nodes, List<Double> distances, List<String> routes) {
        double routeDistance = 0;
        for (int i = start; i < route.length; i++) {
            int temp = route[start];
            route[start] = route[i];
            route[i] = temp;
            permute(route, start + 1, nodes, distances, routes);
            route[i] = route[start];
            route[start] = temp;

        }
        /**/
        if (start == route.length - 1 && route[0] == 1) {
            int currentNode;
            int nextNode;
            for (int i = 0; i < route.length; i++) {
                // check if city is last
                if (i == route.length - 1) {
                    currentNode = route[i];
                    nextNode = route[0];
                } else {
                    currentNode = route[i];
                    nextNode = route[i + 1];
                }
/* Calculating the distance between each 2 cities using euclidean distance formula
 which will give me the distance from city 1 to 2*/
                int currXCord = nodes.get(currentNode - 1).getX();
                int currYCord = nodes.get(currentNode - 1).getY();
                int nextXCord = nodes.get(nextNode - 1).getX();
                int nextYCord = nodes.get(nextNode - 1).getY();
                routeDistance += Math.sqrt((nextXCord - currXCord) * (nextXCord - currXCord)
                        + (nextYCord - currYCord) * (nextYCord - currYCord));
            }
            distances.add(routeDistance);
            routes.add(Arrays.toString(route));

        }

    }

    //
    public static class Node {
        int x;
        int y;
        int city;

        //
        public Node(int x, int y, int city) {
            this.x = x;
            this.y = y;
            this.city = city;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

