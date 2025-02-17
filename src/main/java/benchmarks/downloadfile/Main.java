package benchmarks.downloadfile;

import java.util.Arrays;

import benchmarks.BenchmarkRunner;

import benchmarks.downloadfile.utils.CT;

public class Main {
    public static void main( String[] args ){
        if( args.length < 2 )
            throw new Error( "Must pass two arguments (The size of the list to download and the number of simulations to run)" );
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
            String filename = "output_" + filesize + "_" + simulations + ".txt";

            BenchmarkRunner bmr = new BenchmarkRunner( new CT( filesize ), outputDir, filename );
            bmr.run(simulations);
            
        }
    }
}
