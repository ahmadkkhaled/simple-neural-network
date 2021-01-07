import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class NeuralNetwork {
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
    private void train(){
        for(int epoch = 0; epoch < EPOHCS; epoch++){

        }
    }
    private void addConstantFeature(double value) {
        int height = x.length;
        for (ArrayList<Double> featureVector : x) {
            featureVector.set(0, value);
        }
    }

    private void normalize() {
        throw new NotImplementedException();
    }

    private void initializeLayers(){
        layers = new Layer[3];

        layers[2] = new Layer(n, null);
        layers[1] = new Layer(l, layers[2]);
        layers[0] = new Layer(m, layers[1]);
    }
}
