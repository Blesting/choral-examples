package choral.examples.diffiehellman;

import choral.runtime.Media.SocketByteChannel;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.runtime.serializers.JavaSerializer;
import choral.examples.diffiehellman.DiffieHellman_Bob;
import choral.examples.bipair.BiPair_B;
import java.math.BigInteger;

public class Bob {

    public static void main(String[] args) {
        SerializerChannel_B channel = new SerializerChannel_B( 
                new JavaSerializer(),
				new WrapperByteChannel_B( 
                    SocketByteChannel.connect(
                        Config.ALICE_HOSTNAME, Config.BOB_PORT
                    )
                )
		);

        BigInteger privateKey = new BigInteger("0987654321");
        BiPair_B<BigInteger,BigInteger> result = DiffieHellman_Bob.exchangeKeys(
            channel,
            privateKey,
            Config.SHARED_GENERATOR,
            Config.SHARED_PRIME
        );
    }
}