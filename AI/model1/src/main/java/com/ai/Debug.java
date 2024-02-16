package com.ai;

import java.util.*;

public class Debug {

    public static void main(String[] args) {
        
       Learn run = new Learn();
       
        // for (long i = 0; i < 31; i+=1) { System.out.println(i + "! = " + run.factorial(i)); }
        // System.out.println(
        //     run.reverse("Hello, World!")
        
        //     );
        run.keyGen(991,53,13);

        

    }
}




class Learn{
    
    public int keyGen(int p1, int p2, int p3) {
        int key = -1;
        for (int d = 1; d < p1*p2; d++) {
            if (((d*p3)%(p1-1 * p2-1))==1) {
                key = d;
                break;
            }
        }
        return key;
        
    }
    
    public double keyEnc(double data, double p1, double p2, double p3) {
        double[] pubkey = {p1*p2, p3};
        return (Math.pow(69,pubkey[1]))%pubkey[0];
    }

    // public double keyDec(double ciphert) {
    //     return Math.pow(ciphert, priv);
    // }

    public String reverse(String string) {
        String reversed = "";
        String[] normlist = string.split("");

        for (int i = (normlist.length)-1; i > -1; i--) {
            reversed = reversed.concat(normlist[i]);
        } 

        return reversed;
    }


    public String factorial(long value) {
        long query = 1L;
        String answer = "";
        
        
        for (long i = value; i > 0; i--) {
            query = query * i;
        }
        
        String ans = Long.toString(query);
        int full = ans.length();
        if (full > 10){
            ans = ans.substring(0,10);
            long power = (full) - (ans.length()) +9 -1;
            
            String[] stnd = new String[11];
            String[] anslist = ans.split("");
            stnd[0] = (anslist[0]);
            answer = answer.concat(stnd[0]);
            stnd[1] = ".";
            answer = answer.concat(stnd[1]);
            
            for (int i = 1; i < stnd.length-2; i++) {
                stnd[i] = anslist[i];
                answer = answer.concat(stnd[i]);
                
            }
            answer = answer.concat("x" + power);

        } else {answer = ans;}

        return answer;
    }

    public void debug() {
        
    }
}