package benchmarks.diffiehellman.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.diffiehellman.DiffieHellman_Alice;
import choral.amend.bipair.BiPair_A;
import java.math.BigInteger;

import benchmarks.diffiehellman.Main;

public class Alice {

    public static void main(
        LocalChannel_A channel,
        BigInteger privKey,
        BigInteger sharedPrime,
        BigInteger sharedGenerator
    ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            BiPair_A<BigInteger,BigInteger> result = DiffieHellman_Alice.exchangeKeys(
                channel,
                privKey,
                sharedGenerator,
                sharedPrime
            );
        }
        
        // System.out.println( "Alice: " + result.left() );
    }
}