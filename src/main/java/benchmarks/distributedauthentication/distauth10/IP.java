package benchmarks.distributedauthentication.distauth10;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.distributedauthentication.DistAuth10_IP;



public class IP {
    public static void main( 
        LocalChannel_B channel_Client,
        LocalChannel_B channel_Service,
        LocalChannel_B channel_s1,
        LocalChannel_B channel_s2,
        LocalChannel_B channel_s3,
        LocalChannel_B channel_s4,
        LocalChannel_B channel_s5,
        LocalChannel_B channel_s6,
        LocalChannel_B channel_s7
     ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            DistAuth10_IP ip = new DistAuth10_IP(
                channel_Client, 
                channel_Service,
                channel_s1,
                channel_s2,
                channel_s3,
                channel_s4,
                channel_s5,
                channel_s6,
                channel_s7);

            ip.authenticate();
        }
    }
}
