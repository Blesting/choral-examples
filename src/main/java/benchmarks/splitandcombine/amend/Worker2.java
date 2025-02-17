package benchmarks.splitandcombine.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.splitandcombine.SplitAndCombine_Worker2;



public class Worker2 {
    public static void main( 
        LocalChannel_B channel_Main
    ) throws java.io.IOException {

        SplitAndCombine_Worker2.splitAndCombine( channel_Main );
    }
}
