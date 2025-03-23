package benchmarks.visualizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
import benchmarks.visualizer.utils.Helper;

public class PlotBenchmarksModified {

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
    public static final String[] BENCHMARKS = new String[]{
        "mergesort",
        "quicksort",
        "karatsuba",
        "ssowithretry"};


    // mvn exec:java -Dexec.mainClass="benchmarks.visualizer.PlotBenchmarksModified"
    public static void main( String[] args ){
        // First input: name of the benchmark to plot
        // Second input (optional): name of the outputfile to plot
        Helper helper = new Helper( WARM_UP_REMOVAL, TRIM_AMOUNT );

        if( args.length < 1 ){
            for( String benchmark : BENCHMARKS ){
                plotBenchmark(benchmark, helper.getLargestSimulationFile( TARGET_FOLDER + benchmark + "/modified/" ), helper);
            }
        } else{
            plotBenchmark(args, helper);
        }
    }

    private static void plotBenchmark( String[] args, Helper helper ){ 
        String benchmark = args[0];
        String outputFile = args.length >= 2 ? args[1] : helper.getLargestSimulationFile( TARGET_FOLDER + benchmark + "/modified/" );
        plotBenchmark(benchmark, outputFile, helper);
    }

    private static void plotBenchmark( String benchmark, String outputFile, Helper helper ){
        String benchmarkDir = TARGET_FOLDER + benchmark + "/modified/";

        String exampleBenckmark = benchmarkDir + outputFile;
        String amendBenchmark = benchmarkDir + "amend/" + outputFile;
        String modifiedBenchmark = benchmarkDir + "modified/" + outputFile;

        List<Double> dataList_example = helper.loadData( exampleBenckmark );
        List<Double> dataList_amend = helper.loadData( amendBenchmark );
        List<Double> dataList_modified = helper.loadData( modifiedBenchmark );

        double[] standardDeviations = helper.getStandatdDeviation( dataList_example.stream()
                                                                .map( time -> time.intValue() )
                                                                .toList(),
                                                            dataList_amend.stream()
                                                                .map( time -> time.intValue() )
                                                                .toList(),
                                                            dataList_modified.stream()
                                                                .map( time -> time.intValue() )
                                                                .toList() );
        
        System.out.println( "baseline standard deviation: \t" + standardDeviations[0] );
        System.out.println( "amend standard deviation: \t" + standardDeviations[1] );
        System.out.println( "modified standard deviation: \t" + standardDeviations[2] );

        DefaultCategoryDataset dataset = createCategoryDataset( dataList_example, dataList_amend, dataList_modified );

        JFreeChart chart = createChart( dataset, benchmark );

        exportToSVG(chart, benchmark);
        // displayChart(chart);
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

    private static DefaultCategoryDataset createCategoryDataset( 
        List<Double> dataSet_example, 
        List<Double> dataSet_amend,
        List<Double> dataSet_modified
    ){
        int step = 1;
        List< Integer > exampleTimes = dataSet_example.stream()
            .map( time -> time.intValue() )
            .toList();
        List< Integer > amendTimes = dataSet_amend.stream()
            .map( time -> time.intValue() )
            .toList();
        List< Integer > modifiedTimes = dataSet_modified.stream()
            .map( time -> time.intValue() )
            .toList();
        
        List< Integer > allTimes = Stream.concat( Stream.concat(exampleTimes.stream(), amendTimes.stream()), modifiedTimes.stream() ).toList();
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
                modifiedTimes = modifiedTimes.stream()
                    .map( time -> time % i == i/2 ? time - i/2 : time )
                    .toList();
                allTimes = Stream.concat( Stream.concat(exampleTimes.stream(), amendTimes.stream()), modifiedTimes.stream() ).toList();
                bottom = Collections.min(allTimes);
                top = Collections.max(allTimes);
            }
        }
        
        System.out.println( "min: " + bottom + " , max: " + top );
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for( Integer i = bottom; i <= top; i += step ){
            final Integer FilterValue = i;
            dataset.addValue( exampleTimes.stream().filter( x -> x.equals(FilterValue) ).count() , "Baseline", i.toString() );
            dataset.addValue( amendTimes.stream().filter( x -> x.equals(FilterValue) ).count() , "Inferred comms", i.toString() );
            dataset.addValue( modifiedTimes.stream().filter( x -> x.equals(FilterValue) ).count() , "Manual improvements", i.toString() );
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
        
        String[] splitName = benchmarkName.split("/");
        String filename = splitName[splitName.length-1];

        File file = new File(filename + ".svg");
        try (FileWriter writer = new FileWriter(TARGET_FOLDER + "svgs/modified/" + file)) {
            writer.write(svgElement);
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

}
