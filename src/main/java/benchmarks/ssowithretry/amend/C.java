package benchmarks.ssowithretry.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.ssowithretry.SSOWithRetry_C;
import choral.amend.ssowithretry.utils.Client;



public class C {
    
    public static void main( 
        Client client,
        LocalChannel_A channel_S, 
        LocalChannel_B channel_CAS 
    ) throws java.io.IOException {

        SSOWithRetry_C obj = new SSOWithRetry_C( channel_S, channel_CAS );
        obj.auth( client );
    }

}
