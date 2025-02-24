package benchmarks.visualizer.utils;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;

public class MainWindow {

    private JFrame window;

    public MainWindow(){
        window = new JFrame();
        window.setTitle( "Benchmark visualizer" );
        window.setSize(1600, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }

    public MainWindow( ChartPanel chartPanel ){
        this();
        window.setContentPane( chartPanel );
    }

    public void show(){
        window.setVisible(true);
    }
    
}
