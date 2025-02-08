package benchmarks.quicksort;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.Quicksort.Quicksort_C;



public class C {
    public static void main( 
        LocalChannel_A channel_A,
        LocalChannel_B channel_B
     ) throws java.io.IOException {

        Quicksort_C mergesort = new Quicksort_C(channel_B, channel_A);
        mergesort.sort();
        // System.out.println("Done at C");
    }
}
