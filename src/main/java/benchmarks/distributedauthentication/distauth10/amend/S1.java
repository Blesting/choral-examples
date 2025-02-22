package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.distributedauthentication.DistAuth10_S1;



public class S1 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S1 s1 = new DistAuth10_S1(channel_IP);

        s1.authenticate();
    }
}
