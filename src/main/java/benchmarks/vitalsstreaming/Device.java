package benchmarks.vitalsstreaming;

import choral.runtime.LocalChannel.LocalChannel_A;

import java.util.Stack;

import choral.examples.vitalsstreaming.VitalsStreaming_Device;
import choral.examples.vitalsstreaming.utils.Sensor;
import choral.examples.vitalsstreaming.utils.VitalsMsg;

public class Device {

    public static void main(
        LocalChannel_A channel,
        Sensor sensor
    ) throws java.io.IOException {
        
        Stack<VitalsMsg> stack = sensor.stack();
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            VitalsStreaming_Device device = new VitalsStreaming_Device( channel, new Sensor( (Stack<VitalsMsg>)stack.clone() ) );
            device.gather();
        }
    }
}