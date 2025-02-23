package benchmarks.consumeitems;

import choral.runtime.LocalChannel.LocalChannel_A;

import java.util.ArrayList;
import java.util.List;

import benchmarks.consumeitems.utils.It;
import choral.examples.ConsumeItems.ConsumeItems_A;

public class A {

    public static void main(
        LocalChannel_A channel,
        It it
    ) throws java.io.IOException {
        
        List<Integer> list = it.list();
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            ConsumeItems_A.consumeItems( channel, new It( new ArrayList<>(list) ) );
        }
    }
}