package benchmarks.sendpackets;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.sendpackets.utils.Server;
import choral.examples.sendpackets.SendPackets_S;

public class S {

    public static void main(
        LocalChannel_B channel,
        Server server
    ) throws java.io.IOException {
        
        SendPackets_S.sendPackets( channel, server );
    }
}