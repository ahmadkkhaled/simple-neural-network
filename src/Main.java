import javax.swing.plaf.nimbus.State;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner inputReader = new Scanner(System.in);
    private static NeuralNetwork neuralNetwork;
    private static String userInput = "";
    private static DataObject dataObject;

    private static void entryMenu(){
        if(neuralNetwork == null){
            System.out.println("L | Load Trained Model");
            System.out.println("R | Train a Model");
        }else{
            System.out.println("T | Test Current Model");
        }
        System.out.println("Q | Save Model and Quit");
        System.out.print("Action: ");
    }

    private static void train(){
        System.out.print("Learning Rate? ");
        double learningRate = Double.parseDouble(inputReader.nextLine());

        System.out.print("Number of Iterations? ");
        int iterations = Integer.parseInt(inputReader.nextLine());

        System.out.print("Name of Training Data File (name.extension)? ");
        String fileName = inputReader.nextLine();
        String filepath = System.getProperty("user.dir") + "\\" + fileName;

        try {
            dataObject = DataReader.read(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataObject.normalize(dataObject.getX());
        dataObject.normalize(dataObject.getY());

        neuralNetwork = new NeuralNetwork(learningRate, iterations, dataObject.getM(), dataObject.getL(), dataObject.getN());
        neuralNetwork.train(dataObject.getX(), dataObject.getY());
    }

    private static void test(){
        System.out.print("Name of Testing Data File (name.extension)?");
        String fileName = inputReader.nextLine();
        String filepath = System.getProperty("user.dir") + "\\" + fileName;

        try {
            dataObject = DataReader.read(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataObject.normalize(dataObject.getX());
        dataObject.normalize(dataObject.getY());

        double[] mse = neuralNetwork.accuracy(dataObject.getX(), dataObject.getY());
        double netMSE = 0.0;
        for (int i = 0; i < mse.length; i++)
            netMSE += mse[i];
        System.out.println("MSE = " + netMSE);
    }

    private static void load(){
        System.out.print("State File Name?");
        String fileName = inputReader.nextLine();
        String filePath = System.getProperty("user.dir") + "\\" + fileName;
        try {
            neuralNetwork = StateManager.loadState(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void quit(){
        System.out.print("State File Name?");
        String fileName = inputReader.nextLine();
        String filePath = System.getProperty("user.dir") + "\\" + fileName;

        try {
            StateManager.saveState(neuralNetwork, filePath);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {

        while(!userInput.equals("Q")){
            entryMenu();
            userInput = inputReader.nextLine();
            switch (userInput){
                case "R": {
                    train();
                    break;
                }
                case "T":{
                    test();
                    break;
                }
                case "L":{
                    load();
                    break;
                }
                case "Q":{
                    quit();
                    break;
                }
            }
        }
    }
}

