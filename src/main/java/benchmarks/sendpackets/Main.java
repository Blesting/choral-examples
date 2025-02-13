package benchmarks.sendpackets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;

import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.utils.Server;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (The size of the list to send and the number of simulations to run)" );
        int filesize = Integer.valueOf(args[0]);
        int simulations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        
        MainHelper helper = new MainHelper( outputDir, filesize );
        helper.main( simulations );
    }

    static class MainHelper{

        String outputDir;
        int filesize;

        public MainHelper( String outputDir, int filesize ){
            this.outputDir = outputDir;
            this.filesize = filesize;
        }

        public void main( int simulations ){

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, "output_" + filesize + "_" + simulations + ".txt" );
            bmr.run(simulations);
            
        }

        public class CT implements IterationInitializer{

            public CT(){}

            public List<Thread> initialize(){
                Client client = new Client();
                Server server = new Server( createList() );
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

            private List<Integer> createList(){
                List<Integer> input = new ArrayList<>();
                Random rd = new Random();
                for( int i = 0; i < filesize; i++ ){
                    input.add(rd.nextInt());
                }
                return input;
        
            }
        }
        
    }
}
