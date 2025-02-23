package benchmarks.splitandcombine.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import benchmarks.splitandcombine.Main;
import choral.amend.splitandcombine.SplitAndCombine_Worker1;



public class Worker1 {
    public static void main( 
        LocalChannel_B channel_Main
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SplitAndCombine_Worker1.splitAndCombine( channel_Main );
        }
    }
}
