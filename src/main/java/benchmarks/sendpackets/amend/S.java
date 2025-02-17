package benchmarks.sendpackets.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.sendpackets.utils.Server;
import choral.amend.sendpackets.SendPackets_S;

public class S {

    public static void main(
        LocalChannel_B channel,
        Server server
    ) throws java.io.IOException {
        
        SendPackets_S.sendPackets( channel, server );
    }
}