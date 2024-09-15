package choral.examples.splitandcombine;

import choral.channels.SymChannel;

import choral.examples.splitandcombine.utils.Main;
import choral.examples.splitandcombine.utils.Worker;
import choral.examples.splitandcombine.utils.Tasks;

public class SplitAndCombine@( Main, Worker1, Worker2 ){
    public static Object@Main splitAndCombine( 
        SymChannel@( Main, Worker1 )<Object> ch_MW1, 
        SymChannel@( Main, Worker2 )<Object> ch_MW2,
        Object@Main task 
    ){
        Tasks@Main tasks = Main@Main.split(task);
        Object@Worker1 sub1 = ch_MW1.<Object>com( tasks.first() );
        Object@Worker2 sub2 = ch_MW2.<Object>com( tasks.second() );

        Object@Worker1 res1 = Worker@Worker1.run( sub1 );
        Object@Worker2 res2 = Worker@Worker2.run( sub2 );

        Object@Main res1_M = ch_MW1.<Object>com( res1 );
        Object@Main res2_M = ch_MW2.<Object>com( res2 );
        
        return Main@Main.combine( res1_M, res2_M );
    }
}