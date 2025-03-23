package benchmarks.ssowithretry.modified.utils;

import java.util.ArrayList;
import java.util.List;

import benchmarks.IterationInitializer;
import benchmarks.ssowithretry.modified.*;
import choral.examples.ssowithretry.utils.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_modified implements IterationInitializer{
            
    public CT_modified(){}
    
    public List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        
        Client client = new Client();
        Service service = new Service();
        Authenticator authenticator = new Authenticator();

        MessageQueue CtoS = new MessageQueue();
        MessageQueue StoC = new MessageQueue();
        MessageQueue CtoCAS = new MessageQueue();
        MessageQueue CAStoC = new MessageQueue();
        MessageQueue StoCAS = new MessageQueue();
        MessageQueue CAStoS = new MessageQueue();
        LocalChannel_A ch_StoCAS = new LocalChannel_A(StoCAS, CAStoS);
        LocalChannel_B ch_StoC = new LocalChannel_B(StoC, CtoS);
        LocalChannel_A ch_CAStoC = new LocalChannel_A(CAStoC, CtoCAS);
        LocalChannel_B ch_CAStoS = new LocalChannel_B(CAStoS, StoCAS);
        LocalChannel_A ch_CtoS = new LocalChannel_A(CtoS, StoC);
        LocalChannel_B ch_CtoCAS = new LocalChannel_B(CtoCAS, CAStoC);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    C.main( client, ch_CtoS, ch_CtoCAS );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    S.main( service, ch_StoC, ch_StoCAS );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        Runnable runn3 = new Runnable() {
            public void run(){
                try{
                    CAS.main( authenticator, ch_CAStoS, ch_CAStoC );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn3 ) );

        return threads;
    }
}
