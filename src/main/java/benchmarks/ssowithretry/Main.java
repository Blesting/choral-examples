package benchmarks.ssowithretry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import benchmarks.BenchmarkRunner;
import benchmarks.IterationInitializer;
import choral.examples.ssowithretry.utils.Authenticator;
import choral.examples.ssowithretry.utils.Client;
import choral.examples.ssowithretry.utils.Service;
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

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, "output_" + simulations + ".txt" );
            bmr.run(simulations);
            
        }

        public class CT implements IterationInitializer{
            
            public CT(){}
            
            public List<Thread> initialize(){
                List< Thread > threads = new ArrayList<>();
                
                Client client = new Client();
                Service service = new Service();
                Authenticator authenticator = new Authenticator();

                MessageQueue CtoS = new MessageQueue();
                MessageQueue StoC = new MessageQueue();
                MessageQueue CtoCAS = new MessageQueue();
                MessageQueue CAStoC = new MessageQueue();
                MessageQueue StoCAS = new MessageQueue();
                MessageQueue CAStoS = new MessageQueue();
                LocalChannel_A ch_StoCAS = new LocalChannel_A(StoCAS, CAStoS);
                LocalChannel_B ch_StoC = new LocalChannel_B(StoC, CtoS);
                LocalChannel_A ch_CAStoC = new LocalChannel_A(CAStoC, CtoCAS);
                LocalChannel_B ch_CAStoS = new LocalChannel_B(CAStoS, StoCAS);
                LocalChannel_A ch_CtoS = new LocalChannel_A(CtoS, StoC);
                LocalChannel_B ch_CtoCAS = new LocalChannel_B(CtoCAS, CAStoC);
    
        
                Runnable runn1 = new Runnable() {
                    public void run(){
                        try{
                            C.main( client, ch_CtoS, ch_CtoCAS );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn1 ) );
        
                Runnable runn2 = new Runnable() {
                    public void run(){
                        try{
                            S.main( service, ch_StoC, ch_StoCAS );
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                threads.add( new Thread( runn2 ) );
        
                Runnable runn3 = new Runnable() {
                    public void run(){
                        try{
                            CAS.main( authenticator, ch_CAStoS, ch_CAStoC );
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
