package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.distributedauthentication.DistAuth10_S5;



public class S5 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S5 s5 = new DistAuth10_S5(channel_IP);

        s5.authenticate();
    }
}
