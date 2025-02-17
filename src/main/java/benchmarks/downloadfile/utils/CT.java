package benchmarks.downloadfile.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import benchmarks.IterationInitializer;
import benchmarks.downloadfile.*;
import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.utils.Server;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT implements IterationInitializer {

    int filesize;

    public CT( int filesize ){
        this.filesize = filesize;
    }
            
    public  List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        Client client = new Client();
        Server server = new Server( createList() );


        MessageQueue AtoB = new MessageQueue();
        MessageQueue BtoA = new MessageQueue();
        LocalChannel_B ch_BA = new LocalChannel_B(BtoA, AtoB);
        LocalChannel_A ch_AB = new LocalChannel_A(AtoB, BtoA);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    Downloader.main( ch_AB, "", client );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    Storage.main( ch_BA, server );
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
