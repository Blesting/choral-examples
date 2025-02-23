package benchmarks.splitandcombine;

import choral.runtime.LocalChannel.LocalChannel_A;

import choral.examples.splitandcombine.SplitAndCombine_Main;
import choral.examples.splitandcombine.utils.Result;
import choral.examples.splitandcombine.utils.Task;



public class Host {
    
    public static void main( 
        LocalChannel_A channel_W1, 
        LocalChannel_A channel_W2,
        Task task
    ) throws java.io.IOException {
        

        Result result = new Result(0);
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            result = SplitAndCombine_Main.splitAndCombine(channel_W1, channel_W2, task);
        }
        
        // System.out.println( "result: " + result.value() );
    }
}
