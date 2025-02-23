package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import benchmarks.distributedauthentication.distauth10.Main;
import choral.amend.distributedauthentication.DistAuth10_Client;
import choral.amend.distributedauthentication.utils.Credentials;



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
