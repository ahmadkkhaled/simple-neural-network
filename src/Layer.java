import java.util.Arrays;

public class Layer {
    private double[] value;
    private double[] propagatedError;
    private double[][] w; // w[i][j] the weight of the edge connecting node j of layer H with node i of layer H + 1
    private Layer nextLayer;

    public Layer(int size, Layer nextLayer) {
        this.value = new double[size];
        this.propagatedError = new double[size];
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
        return value.length;
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }

    public double[] getPropagatedError() {
        return propagatedError;
    }

    public void setPropagatedError(double[] propagatedError) {
        this.propagatedError = propagatedError;
    }

    public double[][] getW() {
        return w;
    }

    public void setW(double[][] w) {
        this.w = w;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

}
