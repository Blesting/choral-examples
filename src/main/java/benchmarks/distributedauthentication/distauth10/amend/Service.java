package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.distributedauthentication.DistAuth10_Service;



public class Service {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_Service service = new DistAuth10_Service( channel_IP );

        service.authenticate();
        
    }
}
