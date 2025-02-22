package benchmarks.distributedauthentication.distauth10;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.distributedauthentication.DistAuth10_S4;



public class S4 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S4 s4 = new DistAuth10_S4(channel_IP);

        s4.authenticate();
    }
}
