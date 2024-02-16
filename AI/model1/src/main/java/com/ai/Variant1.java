package com.ai;

import java.util.*;
//import java.io.*;

// import

class Variant1 { // a list of layers
    Layer[] layers;
    int layer, level;

    public Variant1(int layer, int levels) {
        layers = new Layer[layer];
        for (int i = 0; i < levels; i++) {
            layers[i] = new Layer(layer);
        }
        this.layer = layer;
        // this.level = level;
    }

    public void addNeuralNet(double[] newData) {
        for (int i = 0; i < layers.length; i++) {
            layers[i].addLayer(newData);
        }
    }

    // public double handler(double data) {
    // double value = 0.0;
    // return value;
    // }

    public double fowardPropagate(double data) {
        double value = 0.0;

        return value;
    }

    public void addData(double[] data) {
        // for (int i = 0; i < layers; i++) {

        // }
    }

}

class Layer { // a list of levels
    Level[] levels;
    int level;
    // int layer;

    public Layer(int level) {
        levels = new Level[level];
        for (int x = 0; x < levels.length; x++) {
            levels[x] = new Level(level);
        }
        this.level = level;
    }

    public void addLayer(double[] newData) {
        for (int i = 0; i < levels.length; i++) {
            levels[i].addLevel(newData);
        }
    }

    // public double[] foward() {
    // double[] out = new double[levels.length];
    // for (int i = 0; i < levels.length; i++) {
    // out[i] = levels[i].result();
    // }
    // return out;
    // }

    public void foward(double[] nextData) {
        for (int i = 0; i < levels.length; i++) {
            levels[i].addLevel(nextData);
        }
    }
}

class Level { // a list of nodes
    Node[] nodes;
    int levels;

    public Level(int levels) {
        nodes = new Node[levels];
        for (int i = 0; i < levels; i++) {
            nodes[i] = new Node();
        }
        this.levels = levels;
    }

    public void addLevel(double[] newData) {
        for (int i = 0; i < levels; i++) {
            nodes[i].addData(newData);
        }
    }

    public double[] result() {
        double[] out = new double[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            out[i] = nodes[i].activate();
        }
        return out;
    }
}

class Node {
    double[][] core;
    double[] in, out, data;
    double weight, bias, sum;

    public Node() {
        // FileReader reader = new FileReader("./data/weights.txt");
        // try {
        //     FileWriter writer = new FileWriter("./data/weights.txt");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        core = new double[2][2]; // [input[], weight, bias]
        core[0] = new double[3];
        Random random = new Random();
        weight = random.nextDouble();
        bias = random.nextDouble();
        sum = 0;

        // writer.close();
    }

    // public void addNode(double[] newData) {

    //     throw new UnsupportedOperationException("Unimplemented method 'addNode'");
    // }

    public void addData(double[] newData) {

        core[0] = newData;
        // core[0] = data;
        core[1][0] = weight;
        core[1][1] = bias;
        // Node run = new Node();
        // activate();

    }

    public double activate() { // sum of weighted products
        // double[] data = new double[core[0].length];
        // data = core[0];
        while (sum < bias) {
            for (int i = 0; i < core[0].length; i++) {
                sum = sum + (weight * core[0][i]);
            }
        }
        return sum;
    }
}
