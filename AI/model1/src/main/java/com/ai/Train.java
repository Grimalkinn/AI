package com.ai;

import java.util.*;
import java.io.File;
import java.lang.*;
// import javax.sound.midi.Soundbank;

// infinitly run tests,
// manually comparre outputs 
// save closest weights and bias


public class Train {
    NeuralNetwork net;
    boolean run;
    double[] testdata = {0.1,0.2,0.3}, result;

    public Train(){
        // testdata = new double[3];
        run = true;
    }

    public void output(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // System.out.println(Arrays.toString(arr));
            System.out.println(arr[i]);
        }
    }

    public void signatures(double[] data) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        // for (int i = 0; i < testdata.length; i++) { testdata[i] = random.nextDouble(); }
        
        try {
            while (run) {
                int Sqrd = testdata.length;
                net = new NeuralNetwork(Sqrd, Sqrd);

                // double[] response = net.multiFeed(testdata);
                // System.out.print(Arrays.toString(response));
                // output(response);
                // System.out.print(" :: "); 

                // write weights and bias onto file
                // String input = scan.nextLine();
                // if (input.equals("q")){ run = false;
                // } else if (input.equals("s")){ net.save(false); run = false;
                // } else if (input.equals("a")){ net.save(true);
                // } else { ; }
                System.out.println("testdata: "+Arrays.toString(testdata));
                
                result = net.multiFeed(testdata);
                net.save(true);
                run = false;
            }
            
        } catch (Exception e) {
            System.out.println("Err:: "+e);
        }

        scan.close();
        
    }

    public void language() {
        
    }


    public static void main(String[] args) {
        Train train = new Train();

        long start = System.nanoTime();
        train.signatures(train.testdata);
        long end = System.nanoTime();

        // System.out.println("result: "+Arrays.toString(train.result));
        System.out.println("time:" + (double) (end-start)/ 1_000_000_000.0+"s");
        System.out.println("size:" +(new File("C:\\\\Users\\\\Felix Okwuosa\\\\Documents\\\\java\\\\AI\\\\model1\\\\src\\\\main\\\\java\\\\com\\\\ai\\\\signature.data").length() ) +"b");
        // System.out.println("size: " + new File("./signature.data").length());
    }

}

/* test data
0.8792029665944442|0.8170826394059227||0.9223348781058427|0.9961237957301102||0.783097200856834|0.6386334351447611|||
0.714243869278551|0.5880068739803589||0.6099996686038631|0.3323733136459125||0.716960868417114|0.9515693585885635|||
0.7679952627843902|0.9438230179369713||0.4403379631471396|0.6889485301994303||0.9370330114953399|0.292166059506933

weights
0.879 2029665944442| |0.9 223348781058427| |0.78 30972008568340|
0.7 1424 38692785510| |0.6 099996686038631| |0.7 169608684171140|
0.767995 2627843902| |0.4 403379631471396| |0.9 370330114953399|

biases
0.8 170826394059227| |0.996 1237957301102| |0.6 386334351447611|
0.588 0068739803589| |0.33 23733136459125| |0.95 15693585885635|
0.9 438230179369713| |0.6889 485301994303| |0.29 21660595069330|
*/

