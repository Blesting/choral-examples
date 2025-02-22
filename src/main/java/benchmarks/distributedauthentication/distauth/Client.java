package benchmarks.distributedauthentication.distauth;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.distributedauthentication.DistAuth_Client;
import choral.examples.distributedauthentication.utils.Credentials;



public class Client {
    
    public static void main(
        LocalChannel_A channel_IP,
        Credentials credentails
    ) throws java.io.IOException {

        DistAuth_Client client = new DistAuth_Client( channel_IP );

        client.authenticate( credentails );

        
    }

}
