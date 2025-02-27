package benchmarks.visualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;

import benchmarks.visualizer.utils.MainWindow;

public class PlotBenchmarks {

    /** Remove the first n elements to only use datapoints captured on a "warm" system */
    public static final int WARM_UP_REMOVAL = 100;
    /** Remove the top and bottom n percent of elements to decrease the impact of outliers */
    public static final double TRIM_AMOUNT = 0.02;
    /** The location of the benchmarks */
    public static final String TARGET_FOLDER = "target/benchmarks/";
    /** Whether to reduce the amount of bars to show on the graph (done by rounding runtimes to nearest 1ms, 2ms, 4ms ...) */
    public static final boolean REDUCE_STEPS = true;
    /** The maximum number of columns to allow on a graph */
    public static final int MAX_STEPS = 40;
    public static final int SVG_HEIGHT = 600;
    public static final int SVG_WIDTH = 400;


    // mvn exec:java -Dexec.mainClass="benchmarks.utils.PlotBenchmarks" -Dexec.args="consumeitems"
    public static void main( String[] args ){
        // First input: name of the benchmark to plot
        // Second input (optional): name of the outputfile to plot

        String benchmark = args[0];

        String benchmarkDir = TARGET_FOLDER + benchmark + "/";

        String outputFile = args.length >= 2 ? args[1] : getLargestSimulationFile( benchmarkDir );

        String exampleBenckmark = benchmarkDir + outputFile;
        String amendBenchmark = benchmarkDir + "amend/" + outputFile;

        List<Double> dataList_example = loadData( exampleBenckmark );
        List<Double> dataList_amend = loadData( amendBenchmark );

        double[] standardDeviations = getStandatdDeviation( dataList_example.stream()
                                                                .map( time -> time.intValue() )
                                                                .toList(),
                                                            dataList_amend.stream()
                                                                .map( time -> time.intValue() )
                                                                .toList() );
        
        System.out.println( "example standard deviation: \t" + standardDeviations[0] );
        System.out.println( "amend standard deviation: \t" + standardDeviations[1] );

        DefaultCategoryDataset dataset = createCategoryDataset( dataList_example, dataList_amend );

        JFreeChart chart = createChart( dataset, benchmark );

        exportToSVG(chart, benchmark);
        // displayChart(chart);
    }

    private static String getLargestSimulationFile( String dir ){
        List< String > files = Stream.of( new File( dir ).listFiles() )
            .filter( file -> !file.isDirectory() )
            .map( file -> file.getName() )
            .toList();
        System.out.println( "files: " + files );
        
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

    private static int getSimulationsFromFilename( String filename ){
        int lastDot = filename.lastIndexOf(".");
        int lastUnderscore = filename.lastIndexOf("_");
        return Integer.valueOf(filename.substring( lastUnderscore+1, lastDot ));
    }

    private static double getStandatdDeviation( List< Integer > list ){
        double average = list.stream().reduce(0, ( a,b ) -> a+b ) / list.size();

        double sum = list.stream()
            .map( val -> (val - average) * (val - average) )
            .reduce( 0d, ( a,b ) -> a+b );
        double sd = Math.sqrt( sum / (double)list.size() );
        
        return sd;
    }

    private static double[] getStandatdDeviation( List< Integer > list_example, List< Integer > list_amend ){
        double exampleAverage = list_example.stream().reduce(0, ( a,b ) -> a+b ) / list_example.size();
        System.out.println( "example average: " + exampleAverage );

        double amendAverage = list_amend.stream().reduce(0, ( a,b ) -> a+b ) / list_amend.size();
        System.out.println( "amend average: " + amendAverage );


        
        return new double[]{getStandatdDeviation(list_example), getStandatdDeviation(list_amend)};
    }

    private static DefaultCategoryDataset createCategoryDataset( List<Double> dataSet ){
        List< Integer > newDataSet = dataSet.stream()
            .map( time -> time.intValue() )
            .toList();
        
        Integer bottom = Collections.min(newDataSet);
        Integer top = Collections.max(newDataSet);
        System.out.println( "min: " + bottom + " , max: " + top );
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for( Integer i = bottom; i <= top; i++ ){
            final Integer FilterValue = i;
            dataset.addValue( newDataSet.stream().filter( x -> x.equals(FilterValue) ).count() , "occurences", i.toString()  );
        }

        return dataset;
    }

    private static DefaultCategoryDataset createCategoryDataset( List<Double> dataSet_example, List<Double> dataSet_amend ){
        int step = 1;
        List< Integer > exampleTimes = dataSet_example.stream()
            .map( time -> time.intValue() )
            .toList();
        List< Integer > amendTimes = dataSet_amend.stream()
            .map( time -> time.intValue() )
            .toList();
        
        List< Integer > allTimes = Stream.concat(exampleTimes.stream(), amendTimes.stream()).toList();
        Integer bottom = Collections.min(allTimes);
        Integer top = Collections.max(allTimes);
        
        if( REDUCE_STEPS ){
            while( (top - bottom)/step > MAX_STEPS ){
                step = step * 2;
                final int i = step;
                exampleTimes = exampleTimes.stream()
                    .map( time -> time % i == i/2 ? time - i/2 : time )
                    .toList();
                amendTimes = amendTimes.stream()
                    .map( time -> time % i == i/2 ? time - i/2 : time )
                    .toList();
                allTimes = Stream.concat(exampleTimes.stream(), amendTimes.stream()).toList();
                bottom = Collections.min(allTimes);
                top = Collections.max(allTimes);
            }
        }
        
        System.out.println( "min: " + bottom + " , max: " + top );
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for( Integer i = bottom; i <= top; i += step ){
            final Integer FilterValue = i;
            dataset.addValue( exampleTimes.stream().filter( x -> x.equals(FilterValue) ).count() , "example", i.toString()  );
            dataset.addValue( amendTimes.stream().filter( x -> x.equals(FilterValue) ).count() , "amend", i.toString()  );
        }

        return dataset;
    }

    private static JFreeChart createChart( DefaultCategoryDataset dataset, String benchmarkName ){
        JFreeChart chart = ChartFactory.createBarChart(
            benchmarkName + " comparison",
            "Runtimes (ms)",
            "Occurences",
            dataset);
        
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis category = plot.getDomainAxis();
        category.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        category.setCategoryMargin(.3);
        category.setCategoryLabelPositionOffset(10);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setItemMargin( .01 );

        return chart;
    }

    private static void displayChart( JFreeChart chart ){
        MainWindow window = new MainWindow( new ChartPanel(chart) );
        window.show();
    }

    private static void exportToSVG( JFreeChart chart, String benchmarkName ){
        SVGGraphics2D svg = new SVGGraphics2D(SVG_HEIGHT, SVG_WIDTH);

        chart.draw(svg, new java.awt.Rectangle(0, 0, SVG_HEIGHT, SVG_WIDTH));
        
        String svgElement = svg.getSVGElement();

        File file = new File(benchmarkName + ".svg");
        try (FileWriter writer = new FileWriter(TARGET_FOLDER + "svgs/" + file)) {
            writer.write(svgElement);
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    private static List< Double > loadData( String filename ){
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
        System.out.println( "datalist final size: " + dataList.size() );

        return dataList;
    }
}
