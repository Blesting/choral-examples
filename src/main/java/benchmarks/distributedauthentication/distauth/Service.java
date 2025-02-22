package benchmarks.distributedauthentication.distauth;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.distributedauthentication.DistAuth_Service;



public class Service {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth_Service service = new DistAuth_Service( channel_IP );

        service.authenticate();
        
    }
}
