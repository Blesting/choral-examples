package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import benchmarks.distributedauthentication.distauth10.Main;
import choral.amend.distributedauthentication.DistAuth10_S7;



public class S7 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            DistAuth10_S7 s7 = new DistAuth10_S7(channel_IP);

            s7.authenticate();
        }
    }
}
