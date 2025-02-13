package benchmarks.downloadfile;

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
            throw new Error( "Must pass two arguments (The size of the list to download and the number of simulations to run)" );
        int filesize = Integer.valueOf(args[0]);
        int simulations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        System.out.println( outputDir );
        
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

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, "output_" + simulations + ".txt" );
            bmr.run(simulations);
            
        }


        public class CT implements IterationInitializer{
            
            public CT(){}
            
            public  List<Thread> initialize(){
                List< Thread > threads = new ArrayList<>();
                Client client = new Client();
                Server server = new Server( createList() );

    
                MessageQueue AtoB = new MessageQueue();
                MessageQueue BtoA = new MessageQueue();
                LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
                LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
    
        
                Runnable runn1 = new Runnable() {
                    public void run(){
                        try{
                            Downloader.main( ch_AB, "", client );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn1 ) );
        
                Runnable runn2 = new Runnable() {
                    public void run(){
                        try{
                            Storage.main( ch_BA, server );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn2 ) );
        
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
