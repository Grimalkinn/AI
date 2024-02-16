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
    double[] data;
    // String filename = System.getProperty("user.dir").concat("/data/signature.data");
    String filename;

    // Variant2 NN = new Variant2(4, 6);
    NeuralNetwork(int levels, int layers) {
        // public Variant2(int levels, int layers) {
        // filename = (Paths.get("").toAbsolutePath().toString()).concat("/data/signature.data");
        filename = ".\\AI\\model1\\src\\main\\java\\com\\ai\\signature.data";
        this.layers = layers;
        this.levels = levels;
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

    public double[] singleFeed1(double[] data) {
        //loop through a 3D array and 
        for (int y = 0; y < levels; y++) {
            // run mutlithread  
        } return data;
    }

    
    public double[] multiFeed(double[] inputs) {
        for (int i=0; i<layers; i++) {
            int lev = i;
            // Thread thread = new Thread( () -> { fowardPropagate(inputs, i); } );
            // thread.start();
            new Thread( () -> { fowardPropagate(inputs, lev); } ).start();
            // System.out.println("Layer #"+i+" ");
        }
        return data;
    }

    public void set(double[] outputs) { data = outputs; }

    public double[] fowardPropagate(double[] outputs, int lev) {
        // { {{0,0}, {0,1}, {0,2}}, 
        //   {{1,0}, {1,1}, {1,2}},  
        //   {{2,0}, {2,1}, {2,2}} };
        
        // for (int i = 0; i < layers; i++) {
            for (int y = 0; y < levels; y++) {
                // for (int x = 0; x < layers; x++) {
                    // System.out.println("   level #"+x);
                    // data[y] = neuralNet[x][y].process(singleFeed(outputs));
                    data[y] = neuralNet[y][lev].process(outputs);
                    // try { Thread.sleep(1000); } catch (InterruptedException e) {;;}
                    // }
                    outputs = data;
                }
                System.out.println(Arrays.toString(outputs));
        // } 
        return data;
    }

    public void save(boolean append) { 
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append));
            // writer.write(encode());
            writer.append(encode());
            writer.flush();
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
                } else {
                    // Handle invalid data format
                    // You might want to throw an exception or handle this case appropriately
                }
            }
        }
    
        return decodedData;
    }
    
  

}


class Perceptron{
    double weight, bias, output;
    boolean train = false;

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

    public double process(double[] inputs){
        while (output < bias) {
            // output+=(weight * inputs[0])+(weight*inputs[1])+(weight*inputs[2]);
            for (int i = 0; i < inputs.length; i++) { output += (weight * inputs[i]); }
        }
        return output;
    }


}


// END