package com.client.QuizApp;

import java.io.*;
// import java.util.*;

public class Format {
    public static void main(String[] args) {
        new App().run();
    }
}

class App{
    public void run() {
        String[] file;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("requirements.txt"));
            file = reader.readLine().split("\n"); 
            for (int i = 0; i < file.length; i++) {
                // String[] temp = file[i].split(" = \"");
                // file[i] = temp[0]+" == "+temp[1];
                // file[i] = file[i].trim();
                file[i] = file[i].replace(" = \""," == ");
                file[i] = file[i].replace("\"","");
                // System.out.println(file[i]);
            }
            reader.close();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("requirements.txt"));
                for (int i = 0; i < file.length; i++) {
                    writer.write(file[i]);
                }
                writer.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
                
            
        } catch (Exception ex) {System.out.println("file not found" + ex);}


    }
}
