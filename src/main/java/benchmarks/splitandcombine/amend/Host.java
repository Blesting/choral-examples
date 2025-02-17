package benchmarks.splitandcombine.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.splitandcombine.SplitAndCombine_Main;



public class Host {
    
    public static void main( 
        LocalChannel_A channel_W1, 
        LocalChannel_A channel_W2 
    ) throws java.io.IOException {

        SplitAndCombine_Main.splitAndCombine(channel_W1, channel_W2, new Object());
    }

}
