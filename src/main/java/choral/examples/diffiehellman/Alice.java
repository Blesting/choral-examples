package choral.examples.diffiehellman;

import choral.channels.SymDataChannel_A;
import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.Serializers.JavaSerializer;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.examples.diffiehellman.DiffieHellman_Alice;
import choral.examples.bipair.BiPair_A;
import java.math.BigInteger;

public class Alice {

    public static void main(String[] args) throws java.io.IOException {

        ServerSocketByteChannel listener =
            ServerSocketByteChannel.at( 
                Config.ALICE_HOSTNAME, Config.BOB_PORT
            );

        SerializerChannel_A channel = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    listener.getNext()
                )
        );

        BigInteger privateKey = new BigInteger("1234567890");
        BiPair_A<BigInteger,BigInteger> result = DiffieHellman_Alice.exchangeKeys(
            channel,
            privateKey,
            Config.SHARED_GENERATOR,
            Config.SHARED_PRIME
        );
    }
}