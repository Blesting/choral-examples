package benchmarks.sendpackets;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.sendpackets.utils.Server;

import java.util.List;

import choral.examples.sendpackets.SendPackets_S;

public class S {

    public static void main(
        LocalChannel_B channel,
        Server server
    ) throws java.io.IOException {

        List< Integer > list = server.file;
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SendPackets_S.sendPackets( channel, new Server( list ) );
        }
    }
}