package benchmarks.karatsuba.modified.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import benchmarks.IterationInitializer;
import benchmarks.karatsuba.modified.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_modified implements IterationInitializer {

    public CT_modified(){}
            
    public List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        
        Random rd = new Random();
        Long n1 = Math.abs(rd.nextLong());
        Long n2 = Math.abs(rd.nextLong());

        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        MessageQueue AtoC = new MessageQueue();
        MessageQueue CtoA = new MessageQueue();
        MessageQueue BtoC = new MessageQueue();
        MessageQueue CtoB = new MessageQueue();
        LocalChannel_A ch_BC = new LocalChannel_A(BtoC, CtoB);
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_CA = new LocalChannel_A(CtoA, AtoC);
        LocalChannel_B ch_CB = new LocalChannel_B(CtoB, BtoC);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);
        LocalChannel_B ch_AC = new LocalChannel_B(AtoC, CtoA);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    A.main( n1, n2, ch_AB, ch_AC );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    B.main( ch_BA, ch_BC );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        Runnable runn3 = new Runnable() {
            public void run(){
                try{
                    C.main( ch_CB, ch_CA );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn3 ) );

        return threads;
    }
    
}
