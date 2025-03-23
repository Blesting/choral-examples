package benchmarks.karatsuba.modified;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.modified.Karatsuba.Karatsuba_B;



public class B {
    public static void main( 
        LocalChannel_B channel_A,
        LocalChannel_A channel_C
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Karatsuba_B.multiply(channel_A, channel_C);
        }
    }
}
