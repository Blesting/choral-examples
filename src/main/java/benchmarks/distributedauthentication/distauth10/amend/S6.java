package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.distributedauthentication.DistAuth10_S6;



public class S6 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S6 s6 = new DistAuth10_S6(channel_IP);

        s6.authenticate();
    }
}
