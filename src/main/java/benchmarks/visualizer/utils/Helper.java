package benchmarks.visualizer.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Helper {

    /** Remove the first n elements to only use datapoints captured on a "warm" system */
    public int WARM_UP_REMOVAL = 100;
    /** Remove the top and bottom n percent of elements to decrease the impact of outliers */
    public double TRIM_AMOUNT = 0.02;
    /** The location of the benchmarks */
    public String TARGET_FOLDER = "target/benchmarks/"; 

    public Helper(){}

    public Helper( int warmupRemoval, double trim ){
        this.WARM_UP_REMOVAL = warmupRemoval;
        this.TRIM_AMOUNT = trim;
    }

    public Helper( int warmupRemoval, double trim, String targetFolder ){
        this.WARM_UP_REMOVAL = warmupRemoval;
        this.TRIM_AMOUNT = trim;
        this.TARGET_FOLDER = targetFolder;
    }

    public List< Double > loadData( String filename ){
        List<Double> dataList = new ArrayList<>();
        
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dataList.add(Double.valueOf(data));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dataList = dataList.subList(WARM_UP_REMOVAL, dataList.size());
        Collections.sort( dataList);
        
        int trim = (int)(dataList.size()*TRIM_AMOUNT);
        dataList = dataList.subList(trim, dataList.size()-trim);

        return dataList;
    }

    public String getLargestSimulationFile( String dir ){
        List< String > files = Stream.of( new File( dir ).listFiles() )
            .filter( file -> !file.isDirectory() )
            .map( file -> file.getName() )
            .toList();
        
        String mostSimsFile = files.get(0);
        int mostSims = getSimulationsFromFilename(mostSimsFile);

        for( int i = 1; i < files.size(); i++ ){
            String currentFile = files.get(i);
            int currentSims = getSimulationsFromFilename(currentFile);
            if( currentSims > mostSims ){
                mostSimsFile = currentFile;
                mostSims = currentSims;
            }
        }
        return mostSimsFile;
    }

    public int getSimulationsFromFilename( String filename ){
        int lastDot = filename.lastIndexOf(".");
        int lastUnderscore = filename.lastIndexOf("_");
        return Integer.valueOf(filename.substring( lastUnderscore+1, lastDot ));
    }

    public double getStandatdDeviation( List< Integer > list ){
        double average = list.stream().reduce(0, ( a,b ) -> a+b ) / list.size();

        double sum = list.stream()
            .map( val -> (val - average) * (val - average) )
            .reduce( 0d, ( a,b ) -> a+b );
        double sd = Math.sqrt( sum / (double)list.size() );
        
        return sd;
    }

    public double[] getStandatdDeviation( List< Integer > list_example, List< Integer > list_amend ){
        // double exampleAverage = list_example.stream().reduce(0, ( a,b ) -> a+b ) / list_example.size();
        // double amendAverage = list_amend.stream().reduce(0, ( a,b ) -> a+b ) / list_amend.size();
        
        return new double[]{getStandatdDeviation(list_example), getStandatdDeviation(list_amend)};
    }

    public double[] getStandatdDeviation( List< Integer > list_example, List< Integer > list_amend, List< Integer > list_modified ){
        // double exampleAverage = list_example.stream().reduce(0, ( a,b ) -> a+b ) / list_example.size();
        // double amendAverage = list_amend.stream().reduce(0, ( a,b ) -> a+b ) / list_amend.size();
        
        return new double[]{
            getStandatdDeviation(list_example), 
            getStandatdDeviation(list_amend),
            getStandatdDeviation(list_modified)};
    }
}
