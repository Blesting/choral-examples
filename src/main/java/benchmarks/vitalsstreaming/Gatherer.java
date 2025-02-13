package benchmarks.vitalsstreaming;

import choral.runtime.LocalChannel.LocalChannel_B;

import java.util.function.Consumer;

import choral.examples.vitalsstreaming.VitalsStreaming_Gatherer;
import choral.examples.vitalsstreaming.utils.Vitals;

public class Gatherer{

    public static void main(
        LocalChannel_B channel,
        Consumer<Vitals> consumer
    ) throws java.io.IOException {
        
        VitalsStreaming_Gatherer device = new VitalsStreaming_Gatherer( channel );
        device.gather( consumer );
        
    }
}