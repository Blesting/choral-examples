package benchmarks.sendpackets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.nio.file.Files;

import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.utils.Server;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass an argument (the number of simulations to run)" );
        int simulations = Integer.valueOf(args[0]);
        String outputDir;
        if( args.length < 2 ){
            outputDir = "";
        } else{
            String fileSeparator = System.getProperty("file.separator");
            outputDir = String.join( fileSeparator, Arrays.copyOfRange(args, 1, args.length) ) + fileSeparator;
        }
        
        MainHelper helper = new MainHelper( outputDir );
        helper.main( simulations );
    }

    static class MainHelper{

        String outputDir;

        public MainHelper( String outputDir ){
            this.outputDir = outputDir;
        }

        public void main( int simulations ){
            
            List<Float> times = new ArrayList<>();


            for( int sim = 0; sim < simulations; sim++ ){
                List<Thread> threads = createThreads();
                
                try{
                    long startTime = System.nanoTime();     // start timer
                    threads.forEach(t -> t.start());        // start threads
                    for( Thread thread : threads ){
                        thread.join();                      // wait for threads to finish
                    }
                    long endTime = System.nanoTime();       // stop timer
                    
                    float totalTime = (endTime - startTime) / (float)1000000000;
                    times.add(totalTime);

                    // System.out.println( "total time: " + totalTime + " seconds" );
                    System.out.print( "sim " + (sim+1) + " of " + simulations + " done\r" );
                    
                    
                } catch ( Exception e ){
                    e.printStackTrace();
                }
            }

            System.out.println();

            outputStats( times, outputDir + "output_" + simulations + ".txt" );
            
            outputStats( times );
            
        }

        private void outputStats( List<Float> times, String filename ){
            try{
                Files.createDirectories(Paths.get(outputDir));      // create output dir
                FileWriter writer = new FileWriter( filename );     // create output file
                for(Float time : times) {
                    writer.write( time + System.lineSeparator() );  // write output
                }
                writer.close();
            } catch( IOException e ){
                e.printStackTrace();
            }
        }

        private void outputStats( List<Float> times ){
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
            Client client = new Client();
            Server server = new Server();
            List< Thread > threads = new ArrayList<>();

            MessageQueue CtoS = new MessageQueue();
            MessageQueue StoC = new MessageQueue();
            LocalChannel_A ch_CS = new LocalChannel_A(CtoS, StoC);
            LocalChannel_B ch_SC = new LocalChannel_B(StoC, CtoS);

    
            Runnable runnAlice = new Runnable() {
                public void run(){
                    try{
                        C.main( ch_CS, client );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            threads.add( new Thread( runnAlice, "Client" ) );
    
            Runnable runnBob = new Runnable() {
                public void run(){
                    try{
                        S.main( ch_SC, server );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            threads.add( new Thread( runnBob, "Server" ) );
    
            return threads;
        }
    }
}
