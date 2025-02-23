package benchmarks.consumeitems;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;
import benchmarks.consumeitems.amend.utils.CT_amend;
import benchmarks.consumeitems.utils.CT;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 50;
    
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (the length of the iterator and the number of simulations to run)" );
        int iteratorLength = Integer.valueOf(args[0]);
        int simulations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        
        MainHelper helper = new MainHelper( outputDir, iteratorLength );
        helper.main( simulations );
    }

    static class MainHelper{

        String outputDir;
        int iteratorLength;
        
        public MainHelper( 
            String outputDir,
            int iteratorLength 
        ){
            this.outputDir = outputDir;
            this.iteratorLength = iteratorLength;
        }

        public void main( int simulations ){
            String filename = "output_" + simulations + ".txt";

            BenchmarkRunner bmr = new BenchmarkRunner( new CT( iteratorLength ), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend( iteratorLength ), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
    }
}
