package benchmarks.vitalsstreaming.amend;

import choral.runtime.LocalChannel.LocalChannel_B;

import java.util.function.Consumer;

import choral.amend.vitalsstreaming.VitalsStreaming_Gatherer;
import choral.amend.vitalsstreaming.utils.Vitals;

public class Gatherer{

    public static void main(
        LocalChannel_B channel,
        Consumer<Vitals> consumer
    ) throws java.io.IOException {
        
        VitalsStreaming_Gatherer device = new VitalsStreaming_Gatherer( channel );
        device.gather( consumer );
        
    }
}