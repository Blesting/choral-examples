package choral.amend.diffiehellman;

import java.math.BigInteger;
import choral.channels.SymDataChannel;
import choral.amend.bipair.BiPair;

public class DiffieHellman@(Alice,Bob) {

    public static void exchangeKeys (
        SymDataChannel@(Alice,Bob)<Object> channel,
        BigInteger@Alice aPrivKey,
        BigInteger@Bob   bPrivKey,
        BigInteger@Alice aSharedGenerator,
        BigInteger@Bob   bSharedGenerator,
        BigInteger@Alice aSharedPrime,
        BigInteger@Bob   bSharedPrime
    ) {
        /* Step 1: compute public keys. */
        BigInteger@Alice aPubKey = aSharedGenerator.modPow( aPrivKey, aSharedPrime );
        BigInteger@Bob   bPubKey = bSharedGenerator.modPow( bPrivKey, bSharedPrime );
        /* Step 2: exchange public keys. */
        BigInteger@Bob   bRecvKey = channel.<BigInteger>com( aPubKey );
        BigInteger@Alice aRecvKey = channel.<BigInteger>com( bPubKey );
        /* Step 3:compute shared key. */
        BigInteger@Alice aSharedKey = aRecvKey.modPow( aPrivKey, aSharedPrime );
        BigInteger@Bob   bSharedKey = bRecvKey.modPow( bPrivKey, bSharedPrime );
        
        BiPair@(Alice, Bob)<BigInteger,BigInteger> result = 
            new BiPair@(Alice,Bob)<BigInteger,BigInteger>( aSharedKey, bSharedKey );
        
        System@Alice.out.print("result at Alice: "@Alice);
        result.left();
        System@Bob.out.print("result at Bob: "@Bob);
        result.right();
    }

}