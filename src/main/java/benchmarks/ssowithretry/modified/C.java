package benchmarks.ssowithretry.modified;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.modified.ssowithretry.SSOWithRetry_C;
import choral.examples.ssowithretry.utils.Client;



public class C {
    
    public static void main( 
        Client client,
        LocalChannel_A channel_S, 
        LocalChannel_B channel_CAS 
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SSOWithRetry_C obj = new SSOWithRetry_C( channel_S, channel_CAS );
            obj.auth( client );
        }
    }
}
