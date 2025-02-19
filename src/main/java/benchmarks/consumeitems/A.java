package benchmarks.consumeitems;

import choral.runtime.LocalChannel.LocalChannel_A;

import java.util.Iterator;
import choral.examples.ConsumeItems.ConsumeItems_A;

public class A {

    public static void main(
        LocalChannel_A channel,
        Iterator< Integer > it
    ) throws java.io.IOException {
        
        ConsumeItems_A.consumeItems( channel, it );
    }
}