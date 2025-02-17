package benchmarks.vitalsstreaming.amend.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import benchmarks.IterationInitializer;
import benchmarks.vitalsstreaming.amend.*;
import choral.amend.vitalsstreaming.utils.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_amend implements IterationInitializer {
    
    int deviceSize;
    
    public CT_amend( int deviceSize ){
        this.deviceSize = deviceSize;
    }
            
    public  List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        Cons cons = new Cons();
        Stack< VitalsMsg > stack = createStack();
        Sensor sensor = new Sensor( stack );

        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    Gatherer.main( ch_BA, cons );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    Device.main( ch_AB, sensor );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        return threads;
    }

    private Stack< VitalsMsg > createStack(){
        Stack<VitalsMsg> input = new Stack<>();
        Random rd = new Random();
        List<String> signatures = Arrays.asList( "Doctor", "some guy" );
        List<String> ids = Arrays.asList( "1", "2", "3" );
        List<String> heartRates = Arrays.asList( "healthy", "180", "165", "5" );
        List<String> temperatures = Arrays.asList( "0 kelvin", "-273.15 C" );
        List<String> motions = Arrays.asList( "of the ocean" );
        for( int i = 0; i < deviceSize; i++ ){
            Signature signature = new Signature( signatures.get(rd.nextInt(signatures.size())) );
            String id = ids.get(rd.nextInt(ids.size()));
            String heartRate = heartRates.get(rd.nextInt(heartRates.size()));
            String temperature = temperatures.get(rd.nextInt(temperatures.size()));
            String motion = motions.get(rd.nextInt(motions.size()));
            Vitals vitals = new Vitals(id, heartRate, temperature, motion);

            input.push( new VitalsMsg( signature, vitals ) );
        }
        return input;

    }
}
