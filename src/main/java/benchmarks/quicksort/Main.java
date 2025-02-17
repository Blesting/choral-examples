package benchmarks.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;
import benchmarks.quicksort.amend.*;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (length of the list to sort and the number of simulations to run)" );
        int inputLength = Integer.valueOf(args[0]);
        int iterations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        
        
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

        public void main( int simulations ){

            String filename = "output_" + inputLength + "_" + simulations + ".txt";
            
            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, filename );
            bmr.run(simulations);

            bmr = new BenchmarkRunner( new CT_amend(), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }

        public class CT implements IterationInitializer{
            
            public CT(){}

            public List<Thread> initialize(){
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
                            A.main( inputList, ch_AB, ch_AC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnA, "A" ) );
        
                return threads;
            }
        }
        
        public class CT_amend implements IterationInitializer{
            
            public CT_amend(){}

            public List<Thread> initialize(){
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
                            benchmarks.quicksort.amend.B.main( ch_BC, ch_BA );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnB, "B" ) );
        
                Runnable runnC = new Runnable() {
                    public void run(){
                        try{
                            benchmarks.quicksort.amend.C.main( ch_CA, ch_CB );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnC, "C" ) );
        
                Runnable runnA = new Runnable() {
                    public void run(){
                        try{
                            benchmarks.quicksort.amend.A.main( inputList, ch_AB, ch_AC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runnA, "A" ) );
        
                return threads;
            }
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
