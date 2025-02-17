package benchmarks.splitandcombine.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.splitandcombine.SplitAndCombine_Worker1;



public class Worker1 {
    public static void main( 
        LocalChannel_B channel_Main
    ) throws java.io.IOException {

        SplitAndCombine_Worker1.splitAndCombine( channel_Main );
    }
}
