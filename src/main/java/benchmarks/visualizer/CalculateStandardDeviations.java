package benchmarks.visualizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import benchmarks.visualizer.utils.Helper;

public class CalculateStandardDeviations {

    private static final String[] BENCHMARKS = new String[]{
        "consumeitems", 
        "diffiehellman", 
        "distributedauthentication/DistAuth10", 
        "downloadfile",
        "karatsuba",
        "mergesort",
        "quicksort",
        "sendpackets",
        "splitandcombine",
        "ssowithretry",
        "vitalsstreaming"};
    
    public static void main( String[] args ){
        Helper helper = new Helper();

        List< BenchmarkSD > deviations = new ArrayList<>();

        for( int i = 0; i < BENCHMARKS.length; i++ ){
            String benchmark = BENCHMARKS[i];

            BenchmarkSD benchmarkSD = new BenchmarkSD( benchmark );

            String benchmarkDir = helper.TARGET_FOLDER + benchmark + "/";
            String filename = helper.getLargestSimulationFile( benchmarkDir );

            String exampleFile = benchmarkDir + filename;
            String amendFile = benchmarkDir + "amend/" + filename;

            List< Double > exampleData = helper.loadData( exampleFile );
            List< Double > amendData = helper.loadData( amendFile );

            benchmarkSD.setExampleSD( helper.getStandatdDeviation( exampleData.stream()
                                                                        .map( time -> time.intValue() )
                                                                        .toList() ) );
            benchmarkSD.setAmendSD( helper.getStandatdDeviation( amendData.stream()
                                                                        .map( time -> time.intValue() )
                                                                        .toList() ) );

            System.out.println( "standard deviations for " + benchmarkSD.benchmark() + 
                "\n\texample: " + benchmarkSD.exampleSD() + 
                "\n\tamend: " + benchmarkSD.amendSD() );
            
            deviations.add(benchmarkSD);
        }

        writeToFile(deviations);
    }

    private static void writeToFile( List< BenchmarkSD > deviations ){
        Helper helper = new Helper();
        try{
            File file = new File( helper.TARGET_FOLDER + "standardDeviations.csv" );
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write( ",Example,Amend\n" );
            for( BenchmarkSD benchmark : deviations ){
                fileWriter.write( benchmark.benchmark() + "," + String.format( "%.4f", benchmark.exampleSD() ) + "," + String.format( "%.4f", benchmark.amendSD() ) + "\n" );
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    private static class BenchmarkSD{

        private String benchmark;
        private double amendSD;
        private double exampleSD;

        public BenchmarkSD( String benchmark ){
            this.benchmark = benchmark;
        }

        public void setAmendSD( double sd ){
            this.amendSD = sd;
        }

        public void setExampleSD( double sd ){
            this.exampleSD = sd;
        }

        public double amendSD(){
            return amendSD;
        }

        public double exampleSD(){
            return exampleSD;
        }

        public String benchmark(){
            return benchmark;
        }

    }
    
}
