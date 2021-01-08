import java.util.ArrayList;

public class DataObject {
    private int m, l, n;
    private int dataHeight;
    private ArrayList<Double>[] x, y;

    public DataObject(int m, int l, int n, int dataHeight, ArrayList<Double>[] x, ArrayList<Double>[] y) {
        this.m = m;
        this.l = l;
        this.n = n;
        this.dataHeight = dataHeight;
        this.x = x;
        this.y = y;
    }

    public void normalize(ArrayList<Double>[] vectorArray){
        int width = vectorArray[0].size();
        int height = vectorArray.length;

        double[] mean = new double[width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                mean[j] += vectorArray[i].get(j) / height;
            }
        }

        double[] stdv = new double[width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                double error = (mean[j] - vectorArray[i].get(j));
                stdv[j] += (error * error) / height;
            }
        }

        for(int i = 0; i < width; i++)
            stdv[i] = Math.sqrt(stdv[i]);

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                vectorArray[i].set(j, (vectorArray[i].get(j) - mean[j]) / stdv[j]);
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getDataHeight() {
        return dataHeight;
    }

    public void setDataHeight(int dataHeight) {
        this.dataHeight = dataHeight;
    }

    public ArrayList<Double>[] getX() {
        return x;
    }

    public void setX(ArrayList<Double>[] x) {
        this.x = x;
    }

    public ArrayList<Double>[] getY() {
        return y;
    }

    public void setY(ArrayList<Double>[] y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        String layersAlignment = String.valueOf(m) + " " + String.valueOf(l) + " " + String.valueOf(n);
        String height = String.valueOf(dataHeight);

        ret.append(layersAlignment).append("\n").append(height).append("\n");

        for (int i = 0; i < dataHeight; i++) {

            String inputVector = "";
            for (int j = 0; j < m; j++) {
                inputVector += String.valueOf(x[i].get(j)) + " ";
            }

            String outputVector = "";
            for (int j = 0; j < n; j++) {
                outputVector += String.valueOf(y[i].get(j)) + " ";
            }

            ret.append(inputVector).append(outputVector).append("\n");
        }

        return ret.toString();
    }
}
