package benchmarks.diffiehellman;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.examples.diffiehellman.DiffieHellman_Alice;
import choral.examples.bipair.BiPair_A;
import java.math.BigInteger;

public class Alice {

    public static void main(
        LocalChannel_A channel,
        BigInteger privKey,
        BigInteger sharedPrime,
        BigInteger sharedGenerator
    ) throws java.io.IOException {
        
        BiPair_A<BigInteger,BigInteger> result = DiffieHellman_Alice.exchangeKeys(
            channel,
            privKey,
            sharedGenerator,
            sharedPrime
        );
        // System.out.println( "Alice: " + result.left() );
    }
}