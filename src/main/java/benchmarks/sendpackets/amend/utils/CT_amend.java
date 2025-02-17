package benchmarks.sendpackets.amend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import benchmarks.IterationInitializer;
import benchmarks.sendpackets.amend.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_amend implements IterationInitializer{

    int filesize;

    public CT_amend( int filesize ){
        this.filesize = filesize;
    }

    public List<Thread> initialize(){
        choral.amend.sendpackets.utils.Client client = new choral.amend.sendpackets.utils.Client();
        choral.amend.sendpackets.utils.Server server = new choral.amend.sendpackets.utils.Server( createList() );
        List< Thread > threads = new ArrayList<>();

        MessageQueue CtoS = new MessageQueue();
        MessageQueue StoC = new MessageQueue();
        LocalChannel_A ch_CS = new LocalChannel_A(CtoS, StoC);
        LocalChannel_B ch_SC = new LocalChannel_B(StoC, CtoS);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    C.main( ch_CS, client );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    S.main( ch_SC, server );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        return threads;
    }

    private List<Integer> createList(){
        List<Integer> input = new ArrayList<>();
        Random rd = new Random();
        for( int i = 0; i < filesize; i++ ){
            input.add(rd.nextInt());
        }
        return input;

    }
    
}
