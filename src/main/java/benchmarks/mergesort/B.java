package benchmarks.mergesort;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.Mergesort.Mergesort_B;



public class B {
    public static void main( 
        LocalChannel_A channel_C,
        LocalChannel_B channel_A
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Mergesort_B mergesort = new Mergesort_B(channel_A, channel_C);
            mergesort.sort();
        }
        
        // System.out.println("Done at B");
    }
}
