package benchmarks.vitalsstreaming.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.vitalsstreaming.VitalsStreaming_Device;
import choral.amend.vitalsstreaming.utils.Sensor;

public class Device {

    public static void main(
        LocalChannel_A channel,
        Sensor sensor
    ) throws java.io.IOException {
        
        VitalsStreaming_Device device = new VitalsStreaming_Device( channel, sensor );
        device.gather();
        
    }
}