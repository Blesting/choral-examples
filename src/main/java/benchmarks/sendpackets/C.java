package benchmarks.sendpackets;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.SendPackets_C;

public class C {

    public static void main(
        LocalChannel_A channel,
        Client client
    ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SendPackets_C.sendPackets( channel, new Client() );
        }
    }
}