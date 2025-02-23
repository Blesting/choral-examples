package benchmarks.ssowithretry;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.ssowithretry.SSOWithRetry_CAS;
import choral.examples.ssowithretry.utils.Authenticator;



public class CAS {
    public static void main( 
        Authenticator authenticator,
        LocalChannel_B channel_S,
        LocalChannel_A channel_C
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SSOWithRetry_CAS obj = new SSOWithRetry_CAS( channel_S, channel_C );
            obj.auth( authenticator );
        }
    }
}
