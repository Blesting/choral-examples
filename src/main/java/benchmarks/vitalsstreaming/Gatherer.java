package benchmarks.vitalsstreaming;

import choral.runtime.LocalChannel.LocalChannel_B;

import java.util.function.Consumer;

import choral.examples.vitalsstreaming.VitalsStreaming_Gatherer;
import choral.examples.vitalsstreaming.utils.Vitals;

import benchmarks.vitalsstreaming.utils.Cons;;

public class Gatherer{

    public static void main(
        LocalChannel_B channel,
        Consumer<Vitals> consumer
    ) throws java.io.IOException {
        
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            VitalsStreaming_Gatherer device = new VitalsStreaming_Gatherer( channel );
            device.gather( new Cons() );
        }
    }
}