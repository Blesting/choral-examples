package benchmarks.diffiehellman;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;
import benchmarks.diffiehellman.amend.utils.CT_amend;
import benchmarks.diffiehellman.utils.CT;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 1200;
    
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
            String filename = "output_" + simulations + ".txt";

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend(), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
    }
}
