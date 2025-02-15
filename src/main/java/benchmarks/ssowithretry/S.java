package benchmarks.ssowithretry;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.ssowithretry.SSOWithRetry_S;
import choral.examples.ssowithretry.utils.Service;



public class S {
    public static void main( 
        Service service,
        LocalChannel_B channel_C,
        LocalChannel_A channel_CAS
     ) throws java.io.IOException {
        
        SSOWithRetry_S obj = new SSOWithRetry_S( channel_C, channel_CAS );
        obj.auth( service );
    }
}
