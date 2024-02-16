package com.ai;

import java.util.Scanner;

public class Main {
    String userInput;
    boolean run;

    public Main() {
        userInput = "";
        run = true;
    }

    public static void main(String[] args) {
        try {
            Main app = new Main();

            Input input = new Input();
            NeuralNetwork network = new NeuralNetwork(3, 3);
            Output output = new Output();

            Scanner scan = new Scanner(System.in);
            // instansiate other classes

            while (app.run) {
                System.out.print("[Me]:: ");
                app.userInput = scan.nextLine();

                if (app.userInput == "quit" || app.userInput == "exit") {
                    app.run = false;
                } else { // pass input to nn and output
                    System.out.println("[AI]:: " + output.handler(network.multiFeed(input.handler(app.userInput))));
                }
            }
            scan.close();

        } catch (Exception ex) {
            System.out.println("[Err]::" + ex);
        }
    }
}
