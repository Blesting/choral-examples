package benchmarks.mergesort.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import benchmarks.IterationInitializer;
import benchmarks.mergesort.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT implements IterationInitializer {
    
    int inputLength;
    
    public CT( int inputLength ){
        this.inputLength = inputLength;
    }
            
    public List<Thread> initialize(){
        List< Integer > inputList = createList();
        List< Thread > threads = new ArrayList<>();

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
                    B.main( ch_BC, ch_BA );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    C.main( ch_CA, ch_CB );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        Runnable runn3 = new Runnable() {
            public void run(){
                try{
                    A.main( inputList, ch_AB, ch_AC );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn3 ) );

        return threads;
    }

    private List<Integer> createList(){
        List<Integer> input = new ArrayList<>();
        Random rd = new Random();
        for( int i = 0; i < inputLength; i++ ){
            input.add(rd.nextInt());
        }
        return input;

    }
    
}
