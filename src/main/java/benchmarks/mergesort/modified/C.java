package benchmarks.mergesort.modified;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.modified.Mergesort.Mergesort_C;



public class C {
    public static void main( 
        LocalChannel_A channel_A,
        LocalChannel_B channel_B
     ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Mergesort_C mergesort = new Mergesort_C(channel_B, channel_A);
            mergesort.sort();
        }
        
        // System.out.println("Done at C");
    }
}
