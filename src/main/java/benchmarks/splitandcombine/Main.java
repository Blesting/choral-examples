package benchmarks.splitandcombine;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.splitandcombine.amend.utils.CT_amend;
import benchmarks.splitandcombine.utils.CT;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 1000;
    
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (The size of the task and the number of simulations to run)" );
        int taskSize = Integer.valueOf(args[0]);
        int iterations = Integer.valueOf(args[1]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 2, args.length) );
        
        
        MainHelper helper = new MainHelper( taskSize, outputDir );
        helper.main( iterations );
    }

    static class MainHelper{

        String outputDir;
        int taskSize;

        public MainHelper(
            int taskSize,
            String outputDir
        ){
            this.taskSize = taskSize;
            this.outputDir = outputDir;
        }

        public void main( int simulations ){

            String filename = "output_" + simulations + ".txt";
            
            BenchmarkRunner bmr = new BenchmarkRunner( new CT( taskSize ), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend( taskSize ), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
    }
}
