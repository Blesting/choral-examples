package benchmarks.karatsuba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 1 )
            throw new Error( "Must pass an argument (the number of simulations to run)" );
        int iterations = Integer.valueOf(args[0]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 1, args.length) );
        
        
        MainHelper helper = new MainHelper(outputDir);
        helper.main( iterations );
    }

    static class MainHelper{

        String outputDir;

        public MainHelper(
            String outputDir
        ){
            this.outputDir = outputDir;
        }

        public void main( int simulations ){
            String filename = "output_" + simulations + ".txt";

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend(), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }

        public class CT implements IterationInitializer{
            
            public CT(){}
            
            public List<Thread> initialize(){
                List< Thread > threads = new ArrayList<>();
                
                Random rd = new Random();
                Long n1 = rd.nextLong();
                Long n2 = rd.nextLong();
    
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
    
        
                Runnable runn1 = new Runnable() {
                    public void run(){
                        try{
                            A.main( n1, n2, ch_AB, ch_AC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn1 ) );
        
                Runnable runn2 = new Runnable() {
                    public void run(){
                        try{
                            B.main( ch_BA, ch_BC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn2 ) );
        
                Runnable runn3 = new Runnable() {
                    public void run(){
                        try{
                            C.main( ch_CB, ch_CA );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn3 ) );
        
                return threads;
            }
        }

        public class CT_amend implements IterationInitializer{
            
            public CT_amend(){}
            
            public List<Thread> initialize(){
                List< Thread > threads = new ArrayList<>();
                
                Random rd = new Random();
                Long n1 = rd.nextLong();
                Long n2 = rd.nextLong();
    
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
    
        
                Runnable runn1 = new Runnable() {
                    public void run(){
                        try{
                            benchmarks.karatsuba.amend.A.main( n1, n2, ch_AB, ch_AC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn1 ) );
        
                Runnable runn2 = new Runnable() {
                    public void run(){
                        try{
                            benchmarks.karatsuba.amend.B.main( ch_BA, ch_BC );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn2 ) );
        
                Runnable runn3 = new Runnable() {
                    public void run(){
                        try{
                            benchmarks.karatsuba.amend.C.main( ch_CB, ch_CA );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn3 ) );
        
                return threads;
            }
        }
    }
}
