package benchmarks.diffiehellman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;

import java.math.BigInteger;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass an argument (the number of simulations to run)" );
        int simulations = Integer.valueOf(args[0]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 1, args.length) );
        
        MainHelper helper = new MainHelper( outputDir );
        helper.main( simulations );
    }

    static class MainHelper{

        String outputDir;

        public MainHelper( String outputDir ){
            this.outputDir = outputDir;
        }

        public void main( int simulations ){

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, "output_" + simulations + ".txt" );
            bmr.run(simulations);
            
        }


        public class CT implements IterationInitializer{
            
            public CT(){}
            
            public  List<Thread> initialize(){
                BigInteger sharedGenerator = randomBigInteger().abs();
                BigInteger sharedPrime = randomBigInteger().abs();
                BigInteger privKeyAlice = randomBigInteger().abs();
                BigInteger privKeyBob = randomBigInteger().abs();
                List< Thread > threads = new ArrayList<>();
    
                MessageQueue AtoB = new MessageQueue();
                MessageQueue BtoA = new MessageQueue();
                LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
                LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
    
        
                Runnable runnAlice = new Runnable() {
                    public void run(){
                        try{
                            Alice.main( ch_AB, privKeyAlice, sharedPrime, sharedGenerator );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnAlice, "Alice" ) );
        
                Runnable runnBob = new Runnable() {
                    public void run(){
                        try{
                            Bob.main( ch_BA, privKeyBob, sharedPrime, sharedGenerator );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnBob, "Bob" ) );
        
                return threads;
            }
    
            private BigInteger randomBigInteger(){
                Random rd = new Random();
                return new BigInteger( "" + rd.nextInt() );
            }
        }
    }
}
