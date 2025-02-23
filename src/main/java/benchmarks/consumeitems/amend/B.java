package benchmarks.consumeitems.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.ConsumeItems.ConsumeItems_B;
import java.util.function.Consumer;

import benchmarks.consumeitems.Main;
import benchmarks.consumeitems.utils.Cons;


public class B {

    public static void main(
        LocalChannel_B channel,
        Consumer < Integer > consumer
    ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            ConsumeItems_B.consumeItems( channel, new Cons() );
        }
    }
}