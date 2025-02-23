package benchmarks.karatsuba.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import benchmarks.karatsuba.Main;
import choral.amend.Karatsuba.Karatsuba_A;



public class A {
    
    public static void main( 
        Long n1,
        Long n2,
        LocalChannel_A channel_B, 
        LocalChannel_B channel_C 
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            Long result = Karatsuba_A.multiply( n1, n2, channel_B, channel_C );
        }
        
        //System.out.println("result from A: " + result);
    }
}
