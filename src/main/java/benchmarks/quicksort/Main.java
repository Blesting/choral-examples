package benchmarks.quicksort;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.quicksort.utils.CT;
import benchmarks.quicksort.amend.utils.CT_amend;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 1;
    
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
            
            BenchmarkRunner bmr = new BenchmarkRunner( new CT( inputLength ), outputDir, filename );
            bmr.run(simulations);

            bmr = new BenchmarkRunner( new CT_amend( inputLength ), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
        
    }
}
