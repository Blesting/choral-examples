package benchmarks.distributedauthentication.distauth10;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.distributedauthentication.distauth10.utils.CT;
import benchmarks.distributedauthentication.distauth10.amend.utils.CT_amend;

public class Main {
    public static void main( String[] args ){
        if( args.length < 1 )
            throw new Error( "Must pass an argument (the number of simulations to run)" );
        int iterations = Integer.valueOf(args[0]);
        String outputDir = BenchmarkRunner.buildOutputDir( Arrays.copyOfRange(args, 1, args.length) );
        
        
        MainHelper helper = new MainHelper( outputDir );
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

            BenchmarkRunner bmr = new BenchmarkRunner( new CT(), outputDir, filename );
            bmr.run(simulations);
            bmr = new BenchmarkRunner( new CT_amend(), outputDir + "amend/", filename );
            bmr.run(simulations);
            
        }
    }
}
