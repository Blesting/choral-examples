package choral.examples.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main( String[] args ){
        if( args.length != 2 )
            throw new Error( "Must pass exactly two arguments (length of the list to sort and the number of simulations to run)" );
        int inputLength = Integer.valueOf(args[0]);
        int iterations = Integer.valueOf(args[1]);
        // final CyclicBarrier gate = new CyclicBarrier(4);
        MainHelper helper = new MainHelper(inputLength);
        helper.main( iterations );
    }

    static class MainHelper{

        int inputLength;

        public MainHelper(
            int inputLength
        ){
            this.inputLength = inputLength;
        }

        public void main( int iterations ){
            List<Float> times = new ArrayList<>();
            for( int iter = 0; iter < iterations; iter++ ){
                System.out.println( "creating threads" );
                List<Thread> threads = createThreads();
                System.out.println( "starting threads" );
                System.out.println( "starting timer" );
                long startTime = System.nanoTime();
                threads.forEach(t -> t.start());
                try{
                    
                    
                    //gate.await();
                    System.out.println( "waiting for join" );
                    for( Thread thread : threads ){
                        thread.join();
                    }
                    long endTime = System.nanoTime();
                    float totalTime = (endTime - startTime) / (float)1000000000;
                    times.add(totalTime);

                    System.out.println( "total time: " + totalTime + " seconds" );
                    
                    
                } catch ( Exception e ){
                    e.printStackTrace();
                }
            }

            System.out.println( "Times:" );
            for( Float time : times ){
                System.out.println( "\t" + time + " seconds" );
            }
            
            
        }

        private List<Thread> createThreads(){
            List< Integer > inputList = createList();
            List< Thread > threads = new ArrayList<>();
    
            Runnable runnB = new Runnable() {
                public void run(){
                    try{
                        //gate.await();
                        B.main( new String[0] );
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
                        C.main( new String[0] );
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
                        A.main( inputList );
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
