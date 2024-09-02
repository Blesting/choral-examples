package choral.examples.diffiehellman;

import choral.channels.SymChannel_B;
import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.serializers.JavaSerializer;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.examples.diffiehellman.DiffieHellman_Alice;

public class Alice {

    public static void main(String[] args) throws java.io.IOException {

        ServerSocketByteChannel listener =
            ServerSocketByteChannel.at( 
                Config.ALICE_HOSTNAME, Config.BOB_PORT
            );

        SerializerChannel_B channel = new SerializerChannel_B( 
                new JavaSerializer(),
				new WrapperByteChannel_B( 
                    listener.getNext()
                )
        );

    }
}