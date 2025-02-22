package benchmarks.distributedauthentication.distauth10.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.distributedauthentication.DistAuth10_S3;



public class S3 {
    public static void main( 
        LocalChannel_A channel_IP
     ) throws java.io.IOException {
        
        DistAuth10_S3 s3 = new DistAuth10_S3(channel_IP);

        s3.authenticate();
    }
}
