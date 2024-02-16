package com.ai;

import java.io.*;
import java.util.*;

public class Test{

    Test(){
        String name = "felix";
        int age = 10;
    }

    public void print(String string){System.out.println(string);}
    public void print(int integer){System.out.println(integer);}

    public String input(String msg){
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        scan.close();
        return line;
    }
   

    int l;

    public static void main(String[] args) {
        Test run = new Test();
        run.print("Hello, World");
        String name = run.input("enter name: ");
        run.print("your name is " + name);
        // String input = new Scanner(System.in).next();
        // System.out.println(new Scanner(System.in).next());
        
    }   
}