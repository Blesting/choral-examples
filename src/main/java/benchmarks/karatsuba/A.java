package benchmarks.karatsuba;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.Karatsuba.Karatsuba_A;



public class A {
    
    public static void main( 
        Long n1,
        Long n2,
        LocalChannel_A channel_B, 
        LocalChannel_B channel_C 
    ) throws java.io.IOException {

        Long result = Karatsuba_A.multiply( n1, n2, channel_B, channel_C );
        
        //System.out.println("result from A: " + result);
    }

}
