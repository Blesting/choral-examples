package benchmarks.quicksort.amend;

import java.util.List;

import benchmarks.quicksort.Main;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.Quicksort.Quicksort_A;



public class A {
    
    public static void main( 
        List<Integer> input, 
        LocalChannel_A channel_B, 
        LocalChannel_B channel_C 
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Quicksort_A mergesort = new Quicksort_A(channel_B, channel_C);
            List<Integer> sortedList = mergesort.sort(input);
        }
        
        // System.out.println("result from A: " + sortedList);
    }
}
