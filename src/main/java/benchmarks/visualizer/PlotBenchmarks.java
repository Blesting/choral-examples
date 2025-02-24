package benchmarks.visualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import benchmarks.visualizer.utils.MainWindow;

public class PlotBenchmarks {

    /** remove the first n elements to only use datapoints captured on a "warm" system */
    public static final int WARM_UP_REMOVAL = 100;
    /** remove the top and bottom n percent of elements to decrease the impact of outliers */
    public static final double TRIM_AMOUNT = 0.02;

    // mvn exec:java -Dexec.mainClass="benchmarks.utils.PlotBenchmarks" -Dexec.args="target/benchmarks/consumeitems/output_50.txt"
    public static void main( String[] args ){
        
        String filename = args[0];

        List<Double> dataList = loadData( filename );

        getStandatdDeviation( dataList.stream()
                                .map( time -> time.intValue() )
                                .toList() );

        DefaultCategoryDataset dataset = createCategoryDataset(dataList);

        JFreeChart chart = ChartFactory.createBarChart(
            "Simulation Times",
            "Runtimes",
            "Occurences",
            dataset);
        
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis category = plot.getDomainAxis();
        category.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        displayChart(chart);
    }

    private static double getStandatdDeviation( List< Integer > list ){
        double average = list.stream().reduce(0, ( a,b ) -> a+b ) / list.size();
        System.out.println( "average: " + average );

        double sum = list.stream()
            .map( val -> (val - average) * (val - average) )
            .reduce( 0d, ( a,b ) -> a+b );
        double sd = Math.sqrt( sum / (double)list.size() );
        System.out.println( "standard deviation: " + sd );
        
        return sd;
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

    private static void displayChart( JFreeChart chart ){
        MainWindow window = new MainWindow( new ChartPanel(chart) );
        window.show();
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
