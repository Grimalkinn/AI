package com.ai;

// import java.util.Arrays;
// import java.util.Random;

import java.io.*;
import java.util.*;

 
public class NeuralNetwork{

// }class Variant2{
    Perceptron[][] neuralNet;
    int levels, layers, cores;
    double[][][] metadata, randomdata;
    double[] data, limittest;
    // String filename = System.getProperty("user.dir").concat("/data/signature.data");
    String filename;

    // Variant2 NN = new Variant2(4, 6);
    NeuralNetwork(int levels, int layers) {
        // limittest = new double[1000];
        
        // public Variant2(int levels, int layers) {
        // filename = (Paths.get("").toAbsolutePath().toString()).concat("/data/signature.data");
        filename = ".\\AI\\model1\\src\\main\\java\\com\\ai\\signature.data";
        this.layers = layers;
        this.levels = levels;
        
        // only run under heap limit

        if (layers > 1000) { 
            System.out.println("Warning! data limit exceeded by " +(layers-99) +".");
            System.out.print("Do you wish to continue? (Y/n): ");
            Scanner scan = new Scanner(System.in);
            if (scan.nextLine().equals("Y")){ ;; }
            else{ System.exit(-4);} 

            scan.close();
        }

        data = new double[levels];
        // metadata = new double[levels][layers][2];
        try { metadata = decode(); } catch (Exception e) { metadata = new double[levels][layers][2]; }

        neuralNet = new Perceptron[levels][layers];
        randomdata = new double[levels][layers][2];

        for (int y = 0; y < levels; y++) {
            for (int x = 0; x < layers; x++) {
                neuralNet[y][x] = new Perceptron(metadata, y, x);
                randomdata[y][x][0] = neuralNet[y][x].getWeight();
                randomdata[y][x][1] = neuralNet[y][x].getBias();
            }
        }

    }
    
    // NN.singleFeed(processed_input_data);
    public double[] singleFeed(double[] outputs) {
        // double[][][] testdata =  // fockin woks mate
        //     { { {0,0}, {0,0}, {0,0} }, 
        //       { {0,0}, {0,0}, {0,0} },  
        //       { {0,0}, {0,0}, {0,0} } };
        // double[] data = new double[levels];
        // double[] res = new double[levels];
        
        // for (int i = 0; i < layers; i++) {
            for (int y = 0; y < levels; y++) {
                for (int x = 0; x < layers; x++) {
                    // data[y] = neuralNet[x][y].process(singleFeed(outputs));
                    data[y] = neuralNet[x][y].process(outputs);
                    outputs = data;
                }
            }
        // } 
        return data;
    }

    public double[] multiFeed(double[] inputs) {
        Thread[] threads = new Thread[levels];

        for (int i = 0; i < levels; i++) {
            final int lev = i;

            threads[i] = new Thread(() -> forwardPropagate(inputs, lev) );
            threads[i].start();
        }
        // Wait for all threads to finish
        for (Thread thread : threads) {
            try { thread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        } return inputs;
    }

    public void forwardPropagate(double[] outputs, int lev) {
        double[] data = new double[neuralNet[lev].length];
        for (int y = 0; y < neuralNet[lev].length; y++) {
            data[y] = neuralNet[lev][y].process(outputs);
        }

    }

    

    public void save(boolean append) { 
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append));
            if (append) {writer.write(encode() + "\n" );
            } else { writer.write(encode()); }
            // writer.append(encode());
            // writer.flush();
            writer.close();
        } catch(Exception _404_) {
            // File file = new File(filename);
            // try {file.createNewFile();} catch(Exception e) {;}
            // save(false);
            System.out.println("Err::FileNotFound::" +_404_);
        }

    }
    

    public String encode() {
        // String formata:
        // 01011010110101110101101011010111010110101101
        // 0.78650.0..0.0..0.0...0.0..0.0..0.0...0.0..0.0..0.0
        // 0.0.0.0..0.0.0.0..0.0.0.0...0.0.0.0..0.0.0.0..0.0.0.0...0.0.0.0..0.0.0.0..0.0.0.0
        // 0.0.0.0..0.0.0.0..0.0.0.0...0.0.0.0..0.0.0.0..0.0.0.0...0.0.0.0..0.0.0.0..0.0.0.0
        // 0.010.0110.010.0110.010.01110.010.0110.010.0110.010.01110.010.0110.010.0110.010.0
        // 0.0|0.0||0.0|0.0||0.0|0.0|||0.0|0.0||0.0|0.0||0.0|0.0|||0.0|0.0110.0|0.0||0.0|0.0
        // 0.0,0.0][0.0,0.0][0.0,0.0]|[0.0,0.0][0.0,0.0][0.0,0.0]|[0.0,0.0][0.0,0.0][0.0,0.0 :: .=, ..=][ ...=]|[ ends [[[-]]]

        String encodedata = "";
        double[][][] data = randomdata;

        // double[][][] testdata =  // fockin woks mate
        //     { { {0.0,0.0}, {0.0,0.0}, {0.0,0.0} }, 
        //       { {0.0,0.0}, {0.0,0.0}, {0.0,0.0} },  
        //       { {0.0,0.0}, {0.0,0.0}, {0.0,0.0} } };

        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                // encodedata = encodedata.concat("|");
                encodedata = encodedata.concat(Double.toString(data[y][x][0]));
                encodedata = encodedata.concat("|");
                encodedata = encodedata.concat(Double.toString(data[y][x][1]));
                encodedata = encodedata.concat("||");
            } encodedata = encodedata.concat("|");
        } return encodedata.substring(0,encodedata.length()-3);

    }


    public double[][][] decode() {
        double[][][] decodedData = new double[levels][layers][2];
        String filedata = "";
    
        try{
            try {new File(filename).createNewFile();} catch (Exception e) {System.out.println("Err:404:" +e);}
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            filedata = buffer.readLine();
            buffer.close();
        }  catch (Exception ex) {
            System.err.println("Err:404: " + ex);
            // file.close();
        }
        
        // Split the data properly
        String[] stage1 = filedata.split("\\|\\|\\|");
        for (int y = 0; y < stage1.length; y++) {
            String[] stage2 = stage1[y].split("\\|\\|");
            for (int x = 0; x < stage2.length; x++) {
                String[] stage3 = stage2[x].split("\\|");
                // Ensure that stage3 has at least 2 elements
                if (stage3.length >= 2) {
                    decodedData[y][x][0] = Double.valueOf(stage3[0]);
                    decodedData[y][x][1] = Double.valueOf(stage3[1]);
                } else {System.out.println("Error reading data file!");}
            }
        }
    
        return decodedData;
    }
    
  

}


class Perceptron{
    private double weight, bias, output;
    boolean train = true;

    Perceptron(double[][][] metadata, int y, int x){
        if (train || Arrays.deepEquals(metadata, null)){
            Random random = new Random();
            weight = random.nextDouble();
            bias = random.nextDouble();
        } else {
            weight = metadata[y][x][0];
            bias = metadata[y][x][1];
        }
        output = 0;
    }

    public double getWeight() { return weight; }
    public double getBias() { return bias; }

    public double process(double[] inputs) {
        double weightedSum = 0;
        for (int i = 0; i < inputs.length; i++) {
            weightedSum += weight * inputs[i];
        }
        output = weightedSum + bias;
        return output;
    }



}


// END