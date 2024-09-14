package choral.examples.pingpong;

import choral.channels.DiSelectChannel;

import choral.runtime.Serializers.KryoSerializable;

enum Signal@R{ SIG }

public class PingPong@( A, B ){

    public static void signal( 
        DiSelectChannel@( A, B ) ch_AB, 
        DiSelectChannel@( B, A ) ch_BA
    ) {
        try{
            Thread@A.sleep( 1000@A );
            ch_AB.< Signal >select( Signal@A.SIG );
            System@B.out.println("ping"@B);
            PingPong@(B, A).signal( ch_BA, ch_AB);
        } catch ( InterruptedException@A e ){
            System@A.out.println( "Interrupted"@A );
        }
    }
}