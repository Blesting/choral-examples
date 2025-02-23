package benchmarks.karatsuba;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.Karatsuba.Karatsuba_C;



public class C {
    public static void main( 
        LocalChannel_B channel_B,
        LocalChannel_A channel_A
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Karatsuba_C.multiply(channel_B, channel_A);
        }
    }
}
