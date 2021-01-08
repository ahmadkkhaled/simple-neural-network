import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StateManager {
    /**
     * saves 2 weight matrices of a neuralNetwork instance
     * @param neuralNetwork Initialized neuralNetwork object
     */
    public static void saveState(NeuralNetwork neuralNetwork, String filePath) throws IOException {

        File stateFile = new File(filePath);
        stateFile.createNewFile();
        PrintWriter writer = new PrintWriter(stateFile);

        writer.println(neuralNetwork.getEPOHCS());
        writer.println(neuralNetwork.getLEARNING_RATE());

        writer.println(neuralNetwork.getM());
        writer.println(neuralNetwork.getL());
        writer.println(neuralNetwork.getN());

        Layer[] layers = neuralNetwork.getLayers();
        for(int layer_indedx = 0; layer_indedx < layers.length - 1; layer_indedx++){
            double[][] w = layers[layer_indedx].getWeightsMatrix();
            int height = w.length;
            int width = w[0].length;

            writer.println(height);
            writer.println(width);

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    writer.println(w[i][j]);
                }
            }
        }
        writer.flush();
        writer.close();
    }

    public static NeuralNetwork loadState(String filePath) throws IOException{
        File stateFile = new File(filePath);
        Scanner reader = new Scanner(stateFile);

        int epochs = Integer.parseInt(reader.nextLine());
        double learningRate = Double.parseDouble(reader.nextLine());

        int m = Integer.parseInt(reader.nextLine());
        int l = Integer.parseInt(reader.nextLine());
        int n = Integer.parseInt(reader.nextLine());

        NeuralNetwork neuralNetwork = new NeuralNetwork(learningRate, epochs, m, l, n);

        Layer[] layers = new Layer[3];
        for(int layer_index = 0; layer_index < 3; layer_index++){

            if(layer_index == 2){
                layers[layer_index] = new Layer(layers[layer_index - 1].getWeightsMatrix().length, null);
                continue;
            }

            int height = Integer.parseInt(reader.nextLine());
            int width = Integer.parseInt(reader.nextLine());

            layers[layer_index] = new Layer(width, null);

            double[][] w = new double[height][width];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    w[i][j] = Double.parseDouble(reader.nextLine());
                }
            }
            layers[layer_index].setWeightsMatrix(w);
        }

        layers[0].setNextLayer(layers[1]);
        layers[1].setNextLayer(layers[2]);

        neuralNetwork.setLayers(layers);
        return neuralNetwork;
    }

}
