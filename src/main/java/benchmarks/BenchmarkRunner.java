package benchmarks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkRunner {

    IterationInitializer ct;
    String outputDir;
    String filename;
    
    public BenchmarkRunner( IterationInitializer ct, String outputDir, String filename ){
        this.ct = ct;
        this.outputDir = outputDir;
        this.filename = outputDir + filename;
    }
    
    public void run( int simulations ){
            List<Float> times = new ArrayList<>();
            
            for( int sim = 0; sim < simulations; sim++ ){
                List<Thread> threads = ct.initialize();
                
                try{
                    long startTime = System.nanoTime();     // start timer
                    threads.forEach(t -> t.start());        // start threads
                    for( Thread thread : threads ){
                        thread.join();                      // wait for threads to finish
                    }
                    long endTime = System.nanoTime();       // stop timer
                    
                    float totalTime = (endTime - startTime) / (float)1_000_000; // in ms
                    times.add(totalTime);

                    // System.out.println( "total time: " + totalTime + " ms" );
                    System.out.print( "sim " + (sim+1) + " of " + simulations + " done\r" );
                    
                    
                } catch ( Exception e ){
                    e.printStackTrace();
                }
            }
            System.out.println();

            outputStats( times, filename );
            outputStats( times );
            
        }

        private void outputStats( List<Float> times, String filename ){
            try{
                Files.createDirectories(Paths.get(outputDir));      // create output dir
                FileWriter writer = new FileWriter( filename );     // create output file
                for(Float time : times) {
                    writer.write( printFloat( time ) + System.lineSeparator() );  // write output
                }
                writer.close();
            } catch( IOException e ){
                e.printStackTrace();
            }
        }

        private void outputStats( List<Float> times ){
            Collections.sort(times);
            System.out.println( "fastest: " + printFloat( times.get(0) ) );
            System.out.println( "slowest: " + printFloat( times.get(times.size()-1) ) );
            System.out.println( "average: " + printFloat( average(times) ) );
            System.out.println( "median: " + printFloat( times.get(times.size()/2) ) );
        }

        private static Float average( List<Float> list ){
            Float sum = (float)0;
            for( Float elem : list )
                sum += elem;
    
            return sum/list.size();
        }

        private String printFloat( Float value ){
            return String.format( "%.4f", value );
        }

        public static String buildOutputDir( String[] dirs ){
            String outputDir;
            if( dirs.length < 1 ){
                outputDir = "";
            } else{
                String fileSeparator = System.getProperty("file.separator");
                outputDir = String.join( fileSeparator, dirs ) + fileSeparator;
            }

            return outputDir;
        }

}
