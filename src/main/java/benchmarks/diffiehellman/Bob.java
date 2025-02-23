package benchmarks.diffiehellman;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.diffiehellman.DiffieHellman_Bob;
import choral.examples.bipair.BiPair_B;
import java.math.BigInteger;

public class Bob {

    public static void main(
        LocalChannel_B channel,
        BigInteger privKey,
        BigInteger sharedPrime,
        BigInteger sharedGenerator
    ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            BiPair_B<BigInteger,BigInteger> result = DiffieHellman_Bob.exchangeKeys(
                channel,
                privKey,
                sharedGenerator,
                sharedPrime
            );
        }
        
        // System.out.println( "Bob:   " + result.right() );
    }
}