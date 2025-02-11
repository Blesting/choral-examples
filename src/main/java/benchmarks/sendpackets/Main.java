package benchmarks.sendpackets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;

import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.utils.Server;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 1 )
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

            public List<Thread> initialize(){
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
}
