package benchmarks.vitalsstreaming.amend;

import choral.runtime.LocalChannel.LocalChannel_A;

import java.util.Stack;

import benchmarks.vitalsstreaming.Main;
import choral.amend.vitalsstreaming.VitalsStreaming_Device;
import choral.amend.vitalsstreaming.utils.Sensor;
import choral.amend.vitalsstreaming.utils.VitalsMsg;

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