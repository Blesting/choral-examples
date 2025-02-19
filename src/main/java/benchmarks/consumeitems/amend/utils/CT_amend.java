package benchmarks.consumeitems.amend.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import benchmarks.IterationInitializer;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;
import benchmarks.consumeitems.amend.*;
import benchmarks.consumeitems.utils.*;

public class CT_amend implements IterationInitializer {

    int iteratorLength;

    public CT_amend( int iteratorLength ){
        this.iteratorLength = iteratorLength;
    }
            
    public  List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        Iterator< Integer > it = new It( createList() );
        Consumer< Integer > cons = new Cons();

        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);


        Runnable runnAlice = new Runnable() {
            public void run(){
                try{
                    A.main( ch_AB, it );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runnAlice, "Alice" ) );

        Runnable runnBob = new Runnable() {
            public void run(){
                try{
                    B.main( ch_BA, cons );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runnBob, "Bob" ) );

        return threads;
    }

    private List<Integer> createList(){
        List<Integer> input = new ArrayList<>();
        Random rd = new Random();
        for( int i = 0; i < iteratorLength; i++ ){
            input.add(rd.nextInt());
        }
        return input;

    }
    
}
