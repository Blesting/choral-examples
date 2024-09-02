package choral.examples.diffiehellman;

import choral.channels.SymChannel_B;
import choral.examples.diffiehellman.DiffieHellman_Bob;
import choral.runtime.Media.SocketByteChannel;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.runtime.serializers.JavaSerializer;

public class Bob {

    public static void main(String[] args) {
        SerializerChannel_A channel = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    SocketByteChannel.connect(
                        Config.ALICE_HOSTNAME, Config.BOB_PORT
                    )
                )
		);
    }
}