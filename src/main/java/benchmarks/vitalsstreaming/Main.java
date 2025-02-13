package benchmarks.vitalsstreaming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;
import benchmarks.vitalsstreaming.utils.Cons;
import choral.examples.vitalsstreaming.utils.Sensor;
import choral.examples.vitalsstreaming.utils.Signature;
import choral.examples.vitalsstreaming.utils.Vitals;
import choral.examples.vitalsstreaming.utils.VitalsMsg;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (How much data to stream and the number of simulations to run)" );
        int deviceSize = Integer.valueOf(args[0]);
        int simulations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        
        MainHelper helper = new MainHelper( outputDir, deviceSize );
        helper.main( simulations );
    }

    static class MainHelper{

        String outputDir;
        int deviceSize;

        public MainHelper( String outputDir, int deviceSize ){
            this.outputDir = outputDir;
            this.deviceSize = deviceSize;
        }

        public void main( int simulations ){

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, "output_" + deviceSize + "_" + simulations + ".txt" );
            bmr.run(simulations);
            
        }


        public class CT implements IterationInitializer{
            
            public CT(){}
            
            public  List<Thread> initialize(){
                List< Thread > threads = new ArrayList<>();
                Cons cons = new Cons();
                Stack<VitalsMsg> stack = createStack();
                Sensor sensor = new Sensor( stack );
    
                MessageQueue AtoB = new MessageQueue();
                MessageQueue BtoA = new MessageQueue();
                LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
                LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
    
        
                Runnable runn1 = new Runnable() {
                    public void run(){
                        try{
                            Gatherer.main( ch_BA, cons );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn1 ) );
        
                Runnable runn2 = new Runnable() {
                    public void run(){
                        try{
                            Device.main( ch_AB, sensor );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn2 ) );
        
                return threads;
            }

            private Stack<VitalsMsg> createStack(){
                Stack<VitalsMsg> input = new Stack<>();
                Random rd = new Random();
                List<String> signatures = Arrays.asList( "Doctor", "some guy" );
                List<String> ids = Arrays.asList( "1", "2", "3" );
                List<String> heartRates = Arrays.asList( "healthy", "180", "165", "5" );
                List<String> temperatures = Arrays.asList( "0 kelvin", "-273.15 C" );
                List<String> motions = Arrays.asList( "of the ocean" );
                for( int i = 0; i < deviceSize; i++ ){
                    Signature signature = new Signature( signatures.get(rd.nextInt(signatures.size())) );
                    String id = ids.get(rd.nextInt(ids.size()));
                    String heartRate = heartRates.get(rd.nextInt(heartRates.size()));
                    String temperature = temperatures.get(rd.nextInt(temperatures.size()));
                    String motion = motions.get(rd.nextInt(motions.size()));
                    Vitals vitals = new Vitals(id, heartRate, temperature, motion);

                    input.push( new VitalsMsg( signature, vitals ) );
                }
                return input;
        
            }
        }
    }
}
