package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import benchmarks.distributedauthentication.distauth10.Main;
import choral.amend.distributedauthentication.DistAuth10_S3;



public class S3 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            DistAuth10_S3 s3 = new DistAuth10_S3(channel_IP);

            s3.authenticate();
        }
    }
}
