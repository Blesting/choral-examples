package benchmarks.utils;

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

public class PlotBenchmarks {


    // mvn exec:java -Dexec.mainClass="benchmarks.utils.Interpretter" -Dexec.args="target/benchmarks/consumeitems/output_50.txt"
    public static void main( String[] args ){
        
        String filename = args[0];

        List<Double> dataList = loadData( filename );

        getStandatdDeviation( dataList.stream()
                                .map( time -> time.intValue() )
                                .toList() );

        DefaultCategoryDataset dataset = moldData(dataList);

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

    private static DefaultCategoryDataset moldData( List<Double> dataSet ){
        List< Integer > newDataSet = dataSet.stream()
            .map( time -> time.intValue() )
            .toList();
        Integer bottom = Collections.min(newDataSet);
        Integer top = Collections.max(newDataSet);
        System.out.println( "min: " + bottom + " , max: " + top );
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for( Integer i = bottom; i <= top; i++ ){
            final Integer flong = i;
            dataset.addValue( newDataSet.stream().filter( x -> x.equals(flong) ).count() , "occurences", i  );
        }

        return dataset;
    }

    private static void displayChart( JFreeChart chart ){
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setSize(1600, 800);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        dataList = dataList.subList(100, dataList.size());
        Collections.sort( dataList);
        
        int cutoff = (int)(dataList.size()*0.02);
        dataList = dataList.subList(cutoff, dataList.size()-cutoff);
        System.out.println( "datalist final size: " + dataList.size() );

        return dataList;
    }
}
