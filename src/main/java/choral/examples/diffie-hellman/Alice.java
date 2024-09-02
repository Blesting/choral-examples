package choral.examples.diffiehellman;

import choral.channels.SymChannel_B;
import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.serializers.JavaSerializer;
import choral.examples.diffiehellman.DiffieHellman_Alice;

public class Alice {

    public static void main(String[] args) {

        ServerSocketByteChannel listener =
            ServerSocketByteChannel.at( 
                Config.ALICE_HOSTNAME, Config.BOB_PORT
            );

    }
}