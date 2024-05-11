package com.ai;

import java.util.Scanner;

public class Main {
    String userInput, responce;
    boolean run;

    public Main() {
        userInput = "";
        run = true;
    }

    // run code from here 
    public static void main(String[] args) {
        try { // terminal user interface 
            Main app = new Main();
            // process input text, pass into neural netwrok, process output
            Input input = new Input();
            NeuralNetwork network = new NeuralNetwork(3,3);
            Output output = new Output();

            Scanner scan = new Scanner(System.in);
y
            while (app.run) {
                System.out.print("[Me]:: ");
                app.userInput = scan.nextLine();

                if (app.userInput == "quit" || app.userInput == "exit") {
                    app.run = false;
                } else { // single line magic
                    app.responce = output.handler(network.multiFeed(input.handler(app.userInput)));
                    System.out.println("[AI]:: " + app.responce);
                }
            }
            scan.close();

        } catch (Exception ex) {
            System.out.println("[Err]::Main::" + ex);
        }
    }
}
