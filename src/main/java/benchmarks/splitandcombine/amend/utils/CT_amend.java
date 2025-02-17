package benchmarks.splitandcombine.amend.utils;

import java.util.ArrayList;
import java.util.List;

import benchmarks.IterationInitializer;
import benchmarks.splitandcombine.amend.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_amend implements IterationInitializer {

    public CT_amend(){}

    public List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();

        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        MessageQueue AtoC = new MessageQueue();
        MessageQueue CtoA = new MessageQueue();
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_CA = new LocalChannel_A(CtoA, AtoC);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
        LocalChannel_B ch_AC = new LocalChannel_B(AtoC, CtoA);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    Host.main( ch_AB, ch_CA );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    Worker1.main( ch_BA ) ;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        Runnable runn3 = new Runnable() {
            public void run(){
                try{
                    Worker1.main( ch_AC );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn3 ) );

        return threads;
    }
    
}
