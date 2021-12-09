import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* reading the file */
public class FileManager {
    File file;
    List<CityNode> nodes;
    int[] cities;

    /* class constructor */
    public FileManager(File file) {
        this.file = file;
        this.nodes = new ArrayList<>();

    }

    /* adding city positions into an array */
    public void populateCities() {
        cities = new int[nodes.size()];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = nodes.get(i).getPos();
        }
    }

    /* reading file data */
    public void readFileData() {
        Scanner myReader;
        try {
            myReader = new Scanner(this.file);
            while (myReader.hasNextLine()) {
                String lines = myReader.nextLine();
                //using Regex to replace extra spaces and tabs with a single space
                String[] rows = lines.trim().replaceAll("( )+", " ").replaceAll("\t", " ").split(" ");

                int cityNum = Integer.parseInt(rows[0]);
                int x = Integer.parseInt(rows[1]);
                int y = Integer.parseInt(rows[2]);

                nodes.add(new CityNode(x, y, cityNum));

            }
            myReader.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }


    /* getters and setters methods */
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<CityNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<CityNode> nodes) {
        this.nodes = nodes;
    }

    public int[] getCities() {
        return cities;
    }

    public void setCities(int[] cities) {
        this.cities = cities;
    }
}
