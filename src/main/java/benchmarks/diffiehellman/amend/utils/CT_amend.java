package benchmarks.diffiehellman.amend.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import benchmarks.IterationInitializer;
import benchmarks.diffiehellman.amend.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_amend implements IterationInitializer {

    public CT_amend(){}
            
    public  List<Thread> initialize(){
        BigInteger sharedGenerator = randomBigInteger().abs();
        BigInteger sharedPrime = randomBigInteger().abs();
        BigInteger privKeyAlice = randomBigInteger().abs();
        BigInteger privKeyBob = randomBigInteger().abs();
        List< Thread > threads = new ArrayList<>();

        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    Alice.main( ch_AB, privKeyAlice, sharedPrime, sharedGenerator );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    Bob.main( ch_BA, privKeyBob, sharedPrime, sharedGenerator );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2) );

        return threads;
    }

    private BigInteger randomBigInteger(){
        Random rd = new Random();
        return new BigInteger( "" + rd.nextInt() );
    }
    
}
