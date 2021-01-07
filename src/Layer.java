import java.util.ArrayList;
import java.util.Arrays;

public class Layer {
    private double[] values;
    private double[] propagatedErrors;
    private double[][] w; // w[i][j] the weight of the edge connecting node j of layer H with node i of layer H + 1
    private Layer nextLayer;

    public Layer(int size, Layer nextLayer) {
        this.values = new double[size];
        this.propagatedErrors = new double[size];
        this.nextLayer = nextLayer;

        if (nextLayer != null) { // the current layer is not the output layer
            int nextLayerSize = nextLayer.getSize();
            this.w = new double[nextLayerSize][this.getSize()];
            for (double[] row : w) {
                Arrays.fill(row, 1.0);
            }
        }
    }

    public int getSize() {
        return values.length;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public void setValues(ArrayList<Double> values){
        for(int i = 0; i < values.size(); i++) {
            this.values[i] = values.get(i);
        }
    }

    public double[] getPropagatedErrors() {
        return propagatedErrors;
    }

    public void setPropagatedErrors(double[] propagatedErrors) {
        this.propagatedErrors = propagatedErrors;
    }

    public void setPropagatedErrors(ArrayList<Double> propagatedErrors) {
        for(int i = 0; i < propagatedErrors.size(); i++){
            this.propagatedErrors[i] = propagatedErrors.get(i);
        }
    }

    public double[][] getWeightsMatrix() {
        return w;
    }

    public void setWeightsMatrix(double[][] w) {
        this.w = w;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

}
