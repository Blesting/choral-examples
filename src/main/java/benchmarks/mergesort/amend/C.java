package benchmarks.mergesort.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.Mergesort.Mergesort_C;



public class C {
    public static void main( 
        LocalChannel_A channel_A,
        LocalChannel_B channel_B
     ) throws java.io.IOException {

        Mergesort_C mergesort = new Mergesort_C(channel_B, channel_A);
        mergesort.sort();
        // System.out.println("Done at C");
    }
}
