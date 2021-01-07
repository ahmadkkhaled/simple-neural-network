import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class NeuralNetwork { // this model assumes that the node of each layer L is connected to all nodes of next layer L + 1
    private final double LEARNING_RATE;
    private final int EPOHCS;
    private int m, l, n;
    private ArrayList<Double>[] x, y;
    private Layer[] layers;

    public NeuralNetwork(double LEARNING_RATE, int EPOHCS, int m, int l, int n, ArrayList<Double>[] x, ArrayList<Double>[] y) {
        this.LEARNING_RATE = LEARNING_RATE;
        this.EPOHCS = EPOHCS;
        this.m = m;
        this.l = l;
        this.n = n;
        this.x = x;
        this.y = y;

        // TODO normalize
        // TODO add constant feature? (make sure normalization is done first to avoid division by 0)

        initializeLayers();
        train();

    }

    /**
     *
     * @param x feature vector
     * @return predicted output
     */
    public double[] predict (ArrayList<Double> x){
        layers[0].setValues(x);
        for(int i = 0; i <= 1; i++)
            feedForward(layers[i]);
        return layers[2].getValues();
    }

    private void train() {
        for (int epoch = 0; epoch < EPOHCS; epoch++) {
            for (int row = 0; row < x.length; row++) {
                ArrayList<Double> featureVector = x[row];
                ArrayList<Double> outputVector = y[row];

                layers[0].setValues(featureVector);
                for (int i = 0; i <= 1; i++)
                    feedForward(layers[i]);

                layers[2].setPropagatedErrors(outputVector);
                for (int i = 2; i >= 1; i--)
                    calculatePropagatedErrors(layers[i]);

                for (int i = 0; i <= 1; i++)
                    updateWeights(layers[i]);
            }
        }
    }

    private void addConstantFeature(double value) {
        int height = x.length;
        for (ArrayList<Double> featureVector : x) {
            featureVector.set(0, value);
        }
        m += 1;
    }

    private void normalize() {
        throw new NotImplementedException();
    }

    private void initializeLayers() {
        layers = new Layer[3];

        layers[2] = new Layer(n, null);
        layers[1] = new Layer(l, layers[2]);
        layers[0] = new Layer(m, layers[1]);
    }

    private void feedForward(Layer layer) {
        Layer nextLayer = layer.getNextLayer();

        assert (nextLayer != null);

        double[][] w = layer.getWeightsMatrix();

        double[] nextLayerValues = nextLayer.getValues();
        double[] currentLayerValues = layer.getValues();

        for (int i = 0; i < nextLayer.getSize(); i++) {
            double net = LinearAlgebra.dotProduct(currentLayerValues, w[i]);
            nextLayerValues[i] = sigmoid(net);

        }
    }

    private void calculatePropagatedErrors(Layer layer) {
        double[] propagatedErrors = layer.getPropagatedErrors();
        double[] values = layer.getValues();
        double[][] w = layer.getWeightsMatrix();
        Layer nextLayer = layer.getNextLayer();

        // output layer
        if (nextLayer == null) {
            for (int i = 0; i < layer.getSize(); i++) {
                double y_i = propagatedErrors[i]; // propagatedErrors is initialized with outputVector by the calling function, y_i is the i-th component in the output vector
                propagatedErrors[i] = (values[i] - y_i) * values[i] * (1.0 - values[i]);
            }
        } else {
            double[] nextLayerPropagatedErrors = nextLayer.getPropagatedErrors();
            for(int i = 0; i < layer.getSize(); i++){
                double sum = 0.0;

                for(int j = 0; j < nextLayer.getSize(); j++)
                    sum += nextLayerPropagatedErrors[j] * w[j][i] * values[i] * (1.0 - values[i]);


                propagatedErrors[i] = sum;
            }

        }
    }

    private void updateWeights(Layer layer){
        double[][] w = layer.getWeightsMatrix();
        double values[] = layer.getValues();
        double[] nextLayerPropagatedErrors = layer.getNextLayer().getPropagatedErrors();
        for(int i = 0; i < w.length; i++){ // to i at L + 1
            for(int j = 0; j < w[0].length; j++){ // from j at L
                w[i][j] = w[i][j] - LEARNING_RATE * nextLayerPropagatedErrors[i] * values[j];
            }
        }
    }

    private double sigmoid(double value) {
        return (1.0 /
                (1 + Math.exp(-value))
        );
    }
}
