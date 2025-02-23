package benchmarks.ssowithretry.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import benchmarks.ssowithretry.Main;
import choral.amend.ssowithretry.SSOWithRetry_S;
import choral.amend.ssowithretry.utils.Service;



public class S {
    public static void main( 
        Service service,
        LocalChannel_B channel_C,
        LocalChannel_A channel_CAS
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SSOWithRetry_S obj = new SSOWithRetry_S( channel_C, channel_CAS );
            obj.auth( service );
        }
    }
}
