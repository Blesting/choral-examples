package benchmarks.vitalsstreaming;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.vitalsstreaming.utils.CT;
import benchmarks.vitalsstreaming.amend.utils.CT_amend;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 80;
    
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
            String filename = "output_" + deviceSize + "_" + simulations + ".txt";

            BenchmarkRunner bmr = new BenchmarkRunner( new CT( deviceSize ), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend( deviceSize ), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
    }
}
