package benchmarks.consumeitems.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.ConsumeItems.ConsumeItems_B;
import java.util.function.Consumer;


public class B {

    public static void main(
        LocalChannel_B channel,
        Consumer < Integer > consumer
    ) throws java.io.IOException {
        
        ConsumeItems_B.consumeItems( channel, consumer );
    }
}