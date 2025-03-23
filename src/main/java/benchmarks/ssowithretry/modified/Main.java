package benchmarks.ssowithretry.modified;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.ssowithretry.utils.CT;
import benchmarks.ssowithretry.modified.utils.CT_modified;
import benchmarks.ssowithretry.amend.utils.CT_amend;

public class Main {

    public static final int ITERATIONS_PER_SIMULATION = 400;
    
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


            System.out.println( "-= Baseline =-" );
            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, filename );
            bmr.run(simulations);
            
            System.out.println( "-= Inferred comms =-" );
            bmr = new BenchmarkRunner( new CT_amend(), outputDir + "amend/", filename );
            bmr.run(simulations);
            
            System.out.println( "-= Modified =-" );
            bmr = new BenchmarkRunner( new CT_modified(), outputDir + "modified/", filename );
            bmr.run(simulations);
            
        }

        

        
    }
}
