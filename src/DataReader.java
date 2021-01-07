import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    public static DataObject read(String URI) throws FileNotFoundException{
        File dataFile = new File(URI);
        Scanner reader = new Scanner(dataFile);

        int m = reader.nextInt();
        int l = reader.nextInt();
        int n = reader.nextInt();

        int dataHeight = reader.nextInt();

        ArrayList[] x = new ArrayList[dataHeight];
        ArrayList[] y = new ArrayList[dataHeight];

        for(int i = 0; i < dataHeight; i++){
            x[i] = new ArrayList<Double>(m);
            for(int j = 0; j < m; j++){
                Double input = reader.nextDouble();
                x[i].add(input);
            }

            y[i] = new ArrayList<Double>(n);
            for(int j = 0; j < n; j++){
                double input = reader.nextDouble();
                y[i].add(input);
            }
        }

        return new DataObject(m, l, n, dataHeight, x, y);
    }
}
