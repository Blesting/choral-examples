package benchmarks.distributedauthentication.distauth10;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.distributedauthentication.DistAuth10_Client;
import choral.examples.distributedauthentication.utils.Credentials;



public class Client {
    
    public static void main(
        LocalChannel_A channel_IP,
        Credentials credentails
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            DistAuth10_Client client = new DistAuth10_Client( channel_IP );

            client.authenticate( credentails );
        }
    }

}
