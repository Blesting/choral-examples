package benchmarks.karatsuba.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import benchmarks.karatsuba.Main;
import choral.amend.Karatsuba.Karatsuba_B;



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
