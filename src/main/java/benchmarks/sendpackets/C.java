package benchmarks.sendpackets;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.SendPackets_C;

public class C {

    public static void main(
        LocalChannel_A channel,
        Client client
    ) throws java.io.IOException {
        
        SendPackets_C.sendPackets( channel, client );
    }
}