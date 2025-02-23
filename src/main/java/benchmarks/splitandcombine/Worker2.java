package benchmarks.splitandcombine;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.splitandcombine.SplitAndCombine_Worker2;



public class Worker2 {
    public static void main( 
        LocalChannel_B channel_Main
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SplitAndCombine_Worker2.splitAndCombine( channel_Main );
        }
    }
}
