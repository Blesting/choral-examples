package benchmarks.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (length of the list to sort and the number of simulations to run)" );
        int inputLength = Integer.valueOf(args[0]);
        int iterations = Integer.valueOf(args[1]);
        String outputDir;
        if( args.length < 3 ){
            outputDir = "";
        } else{
            String fileSeparator = System.getProperty("file.separator");
            outputDir = String.join( fileSeparator, Arrays.copyOfRange(args, 2, args.length) ) + fileSeparator;
        }
        
        // final CyclicBarrier gate = new CyclicBarrier(4);
        MainHelper helper = new MainHelper(inputLength, outputDir);
        helper.main( iterations );
    }

    static class MainHelper{

        int inputLength;
        String outputDir;

        public MainHelper(
            int inputLength,
            String outputDir
        ){
            this.inputLength = inputLength;
            this.outputDir = outputDir;
        }

        public void main( int iterations ){
            List<Float> times = new ArrayList<>();
            for( int iter = 0; iter < iterations; iter++ ){
                List<Thread> threads = createThreads();
                threads.forEach(t -> t.start());
                try{
                    long startTime = System.nanoTime();
                    //gate.await();
                    for( Thread thread : threads ){
                        thread.join();
                    }
                    long endTime = System.nanoTime();
                    float totalTime = (endTime - startTime) / (float)1000000000;
                    times.add(totalTime);

                    // System.out.println( "total time: " + totalTime + " seconds" );
                    System.out.println( "sim " + (iter+1) + " of " + iterations + " done" );
                    
                    
                } catch ( Exception e ){
                    e.printStackTrace();
                }
            }

            try{
                Files.createDirectories(Paths.get(outputDir));
                FileWriter writer = new FileWriter( outputDir + "output_" + inputLength + "_" + iterations + ".txt"); 
                for(Float time : times) {
                    writer.write( time + System.lineSeparator() );
                }
                writer.close();
            } catch( IOException e ){
                e.printStackTrace();
            }
            Collections.sort(times);
            System.out.println( "fastest: " + times.get(0) );
            System.out.println( "slowest: " + times.get(times.size()-1) );
            System.out.println( "average: " + average(times) );
            System.out.println( "median: " + times.get(times.size()/2) );
            
        }

        private static Float average( List<Float> list ){
            Float sum = (float)0;
            for( Float elem : list )
                sum += elem;
    
            return sum/list.size();
        }

        private List<Thread> createThreads(){
            List< Integer > inputList = createList();
            List< Thread > threads = new ArrayList<>();

            MessageQueue AtoB = new MessageQueue();
            MessageQueue BtoA = new MessageQueue();
            MessageQueue AtoC = new MessageQueue();
            MessageQueue CtoA = new MessageQueue();
            MessageQueue BtoC = new MessageQueue();
            MessageQueue CtoB = new MessageQueue();
            LocalChannel_A ch_BC = new LocalChannel_A(BtoC, CtoB);
            LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
            LocalChannel_A ch_CA = new LocalChannel_A(CtoA, AtoC);
            LocalChannel_B ch_CB = new LocalChannel_B(CtoB, BtoC);
            LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
            LocalChannel_B ch_AC = new LocalChannel_B(AtoC, CtoA);

    
            Runnable runnB = new Runnable() {
                public void run(){
                    try{
                        //gate.await();
                        B.main( ch_BC, ch_BA );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            threads.add( new Thread( runnB, "B" ) );
    
            Runnable runnC = new Runnable() {
                public void run(){
                    try{
                        //gate.await();
                        C.main( ch_CA, ch_CB );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            threads.add( new Thread( runnC, "C" ) );
    
            Runnable runnA = new Runnable() {
                public void run(){
                    try{
                        //gate.await();
                        A.main( inputList, ch_AB, ch_AC );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            threads.add( new Thread( runnA, "A" ) );
    
            return threads;
        }

        private List<Integer> createList(){
            List<Integer> input = new ArrayList<>();
            Random rd = new Random();
            for( int i = 0; i < inputLength; i++ ){
                input.add(rd.nextInt());
            }
            return input;
    
        }
    }
}
