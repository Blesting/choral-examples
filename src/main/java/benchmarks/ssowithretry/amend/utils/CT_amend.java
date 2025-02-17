package benchmarks.ssowithretry.amend.utils;

import java.util.ArrayList;
import java.util.List;

import benchmarks.IterationInitializer;
import benchmarks.ssowithretry.amend.*;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT_amend implements IterationInitializer{
            
    public CT_amend(){}
    
    public List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        
        choral.amend.ssowithretry.utils.Client client = new choral.amend.ssowithretry.utils.Client();
        choral.amend.ssowithretry.utils.Service service = new choral.amend.ssowithretry.utils.Service();
        choral.amend.ssowithretry.utils.Authenticator authenticator = new choral.amend.ssowithretry.utils.Authenticator();

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
