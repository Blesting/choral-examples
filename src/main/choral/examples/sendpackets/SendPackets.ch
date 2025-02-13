package choral.examples.sendpackets;

import choral.channels.SymChannel;
import choral.examples.sendpackets.utils.Server;
import choral.examples.sendpackets.utils.Client;

import choral.runtime.Serializers.KryoSerializable;

enum Stream@R{ NEXT, END }

public class SendPackets@( C, S ){

    public static void sendPackets( 
        SymChannel@( C, S )<Object> channel, 
        Server@S server, 
        Client@C client 
    ) {
        if (server.n < server.packets(server.file)){
            channel.<Stream>select(Stream@S.NEXT);
            Integer@C packet = channel.<Integer>com( server.mkPacket( server.file, server.n ) );
            client.file = client.append( client.file, packet );
            server.n += 1@S;
            sendPackets( channel, server, client );
        }
        else{
            channel.<Stream>select(Stream@S.END);
        }
    }
}