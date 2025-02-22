package benchmarks.distributedauthentication.distauth;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.distributedauthentication.DistAuth_IP;



public class IP {
    public static void main( 
        LocalChannel_B channel_Client,
        LocalChannel_B channel_Service
     ) throws java.io.IOException {

        DistAuth_IP ip = new DistAuth_IP(channel_Client, channel_Service);

        ip.authenticate();

    }
}
