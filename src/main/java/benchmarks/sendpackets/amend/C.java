package benchmarks.sendpackets.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.sendpackets.utils.Client;
import benchmarks.sendpackets.Main;
import choral.amend.sendpackets.SendPackets_C;

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