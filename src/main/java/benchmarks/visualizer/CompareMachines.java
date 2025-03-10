package benchmarks.visualizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;

import benchmarks.visualizer.utils.Helper;

public class CompareMachines {

    /** Remove the first n elements to only use datapoints captured on a "warm" system */
    public static final int WARM_UP_REMOVAL = 100;
    /** Remove the top and bottom n percent of elements to decrease the impact of outliers */
    public static final double TRIM_AMOUNT = 0.02;
    /** The location of the benchmarks */
    public static final String OUTPUT_FOLDER = "target/machine-comparison/";
    /** Whether to reduce the amount of bars to show on the graph (done by rounding runtimes to nearest 1ms, 2ms, 4ms ...) */
    public static final boolean REDUCE_STEPS = true;
    /** The maximum number of columns to allow on a graph */
    public static final int MAX_STEPS = 40;
    public static final int SVG_HEIGHT = 600;
    public static final int SVG_WIDTH = 400;
    public static final String[] BENCHMARKS = new String[]{
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
    public static final String[] MACHINE_FOLDERS = new String[]{
        "target/benchmarks/",
        "target/u1-standard-1/benchmarks/",
        "target/u1-standard-4/benchmarks/"
    };
    public static final String[] MACHINES = new String[]{
        "Local",
        "u1-standard-1",
        "u1-standard-4"
    };

    // mvn exec:java -Dexec.mainClass="benchmarks.visualizer.CompareMachines"
    public static void main( String[] args ){
        Helper helper = new Helper( WARM_UP_REMOVAL, TRIM_AMOUNT );

        for( String benchmark : BENCHMARKS ){
            String outputFile = helper.getLargestSimulationFile( MACHINE_FOLDERS[0] + benchmark + "/" ); 
            List< List<Double> > dataLists_example = new ArrayList<>();
            List< List<Double> > dataLists_amend = new ArrayList<>();
            List< Double > standardDeviations_example = new ArrayList<>();
            List< Double > standardDeviations_amend = new ArrayList<>();
            
            for( String machine : MACHINE_FOLDERS ){
                String benchmarkDir = machine + benchmark + "/";

                String exampleBenckmark = benchmarkDir + outputFile;
                String amendBenchmark = benchmarkDir + "amend/" + outputFile;

                List<Double> dataList_example = helper.loadData( exampleBenckmark );
                List<Double> dataList_amend = helper.loadData( amendBenchmark );

                dataLists_example.add(dataList_example);
                dataLists_amend.add(dataList_amend);

                standardDeviations_example.add(helper.getStandatdDeviation( dataList_example.stream().map( time -> time.intValue() ).toList()) );
                standardDeviations_amend.add(helper.getStandatdDeviation( dataList_amend.stream().map( time -> time.intValue() ).toList()) );
            }
            
            DefaultCategoryDataset dataset_example = createCategoryDataset( dataLists_example );
            DefaultCategoryDataset dataset_amend = createCategoryDataset( dataLists_amend );

            JFreeChart chart_example = createChart( dataset_example, benchmark, " manual comms" );
            JFreeChart chart_amend = createChart( dataset_amend, benchmark, " inferred comms" );

            exportToSVG(chart_example, benchmark, "example");
            exportToSVG(chart_amend, benchmark, "amend");

            writeSDs(helper, benchmark, standardDeviations_example, standardDeviations_amend);
        }
    }

    private static DefaultCategoryDataset createCategoryDataset( List< List<Double> > dataSets ){
        int step = 1;
        List< List<Integer> > times = dataSets.stream()
            .map( list -> list.stream().map( time -> time.intValue() ).toList() )
            .toList();
        
        List< Integer > allTimes = times.stream().flatMap( List::stream ).toList();
        Integer bottom = Collections.min(allTimes);
        Integer top = Collections.max(allTimes);
        
        if( REDUCE_STEPS ){
            while( (top - bottom)/step > MAX_STEPS ){
                step = step * 2;
                final int i = step;
                for( List<Integer> list : times ){
                    list = list.stream()
                    .map( time -> time % i == i/2 ? time - i/2 : time )
                    .toList();
                }
                allTimes = times.stream().flatMap( List::stream ).toList();
                bottom = Collections.min(allTimes);
                top = Collections.max(allTimes);
            }
        }
        
        System.out.println( "min: " + bottom + " , max: " + top );
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for( Integer i = bottom; i <= top; i += step ){
            final Integer FilterValue = i;
            for( int idx = 0; idx < times.size(); idx++ ){
                List<Integer> datalist = times.get(idx);
                dataset.addValue( datalist.stream().filter( x -> x.equals(FilterValue) ).count() , MACHINES[idx], i.toString()  );
            }
        }

        return dataset;
    }

    private static JFreeChart createChart( DefaultCategoryDataset dataset, String benchmarkName, String name ){
        JFreeChart chart = ChartFactory.createBarChart(
            benchmarkName + name + " comparison",
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

    private static void exportToSVG( JFreeChart chart, String benchmarkName, String name ){
        SVGGraphics2D svg = new SVGGraphics2D(SVG_HEIGHT, SVG_WIDTH);

        chart.draw(svg, new java.awt.Rectangle(0, 0, SVG_HEIGHT, SVG_WIDTH));
        
        String svgElement = svg.getSVGElement();

        String[] splitName = benchmarkName.split("/");
        String filename = splitName[splitName.length-1];

        File file = new File(filename + "_" + name + ".svg");
        String folder = OUTPUT_FOLDER + "svgs/" + filename + "/";
        new File( folder ).mkdirs();
        try (FileWriter writer = new FileWriter(folder + file)) {
            writer.write(svgElement);
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    private static void writeSDs( Helper helper, String benchmark, List< Double > sd_example, List< Double > sd_amend ){
        String[] splitName = benchmark.split("/");
        String filename = splitName[splitName.length-1];
        
        try{
            File file = new File( OUTPUT_FOLDER + "standardDeviations_" + filename + ".csv" );
            FileWriter fileWriter = new FileWriter(file);
            for( String machine : MACHINES ){
                fileWriter.write( ";" + machine );
            }
            
            fileWriter.write( "\nManual comms" );
            for( Double sd : sd_example ){
                fileWriter.write( ";" + String.format( "%.4f", sd ) );
            }
            fileWriter.write( "\nInferred comms" );
            for( Double sd : sd_amend ){
                fileWriter.write( ";" + String.format( "%.4f", sd ) );
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
