package benchmarks.distributedauthentication.distauth10;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.distributedauthentication.DistAuth10_S2;



public class S2 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S2 s2 = new DistAuth10_S2(channel_IP);

        s2.authenticate();
    }
}
