package benchmarks.quicksort.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import benchmarks.quicksort.Main;
import choral.amend.Quicksort.Quicksort_C;



public class C {
    public static void main( 
        LocalChannel_A channel_A,
        LocalChannel_B channel_B
     ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Quicksort_C mergesort = new Quicksort_C(channel_B, channel_A);
            mergesort.sort();
        }
        
        // System.out.println("Done at C");
    }
}
