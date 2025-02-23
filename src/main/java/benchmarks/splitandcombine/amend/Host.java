package benchmarks.splitandcombine.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import benchmarks.splitandcombine.Main;
import choral.amend.splitandcombine.SplitAndCombine_Main;
import choral.amend.splitandcombine.utils.Task;



public class Host {
    
    public static void main( 
        LocalChannel_A channel_W1, 
        LocalChannel_A channel_W2,
        Task task 
    ) throws java.io.IOException {

        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            SplitAndCombine_Main.splitAndCombine(channel_W1, channel_W2, task);
        }
    }
}
