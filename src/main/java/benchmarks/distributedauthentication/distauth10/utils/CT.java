package benchmarks.distributedauthentication.distauth10.utils;

import java.util.ArrayList;
import java.util.List;

import benchmarks.IterationInitializer;
import benchmarks.distributedauthentication.distauth10.*;
import choral.examples.distributedauthentication.utils.Credentials;
import choral.runtime.LocalChannel.LocalChannel_A;
import choral.runtime.LocalChannel.LocalChannel_B;
import choral.runtime.Media.MessageQueue;

public class CT implements IterationInitializer {
    
    public CT(){}
            
    public List<Thread> initialize(){
        List< Thread > threads = new ArrayList<>();
        Credentials credentials = new Credentials( "Username" , "Password" );

        MessageQueue ClienttoIP = new MessageQueue();
        MessageQueue IPtoClient = new MessageQueue();
        MessageQueue ServicetoIP = new MessageQueue();
        MessageQueue IPtoService = new MessageQueue();
        MessageQueue S1toIP = new MessageQueue();
        MessageQueue IPtoS1 = new MessageQueue();
        MessageQueue S2toIP = new MessageQueue();
        MessageQueue IPtoS2 = new MessageQueue();
        MessageQueue S3toIP = new MessageQueue();
        MessageQueue IPtoS3 = new MessageQueue();
        MessageQueue S4toIP = new MessageQueue();
        MessageQueue IPtoS4 = new MessageQueue();
        MessageQueue S5toIP = new MessageQueue();
        MessageQueue IPtoS5 = new MessageQueue();
        MessageQueue S6toIP = new MessageQueue();
        MessageQueue IPtoS6 = new MessageQueue();
        MessageQueue S7toIP = new MessageQueue();
        MessageQueue IPtoS7 = new MessageQueue();
        LocalChannel_A ch_Client_IP = new LocalChannel_A(ClienttoIP, IPtoClient);
        LocalChannel_B ch_IP_Client = new LocalChannel_B(IPtoClient, ClienttoIP);
        LocalChannel_A ch_Service_IP = new LocalChannel_A(ServicetoIP, IPtoService);
        LocalChannel_B ch_IP_Service = new LocalChannel_B(IPtoService, ServicetoIP);
        LocalChannel_A ch_S1_IP = new LocalChannel_A(S1toIP, IPtoS1);
        LocalChannel_B ch_IP_S1 = new LocalChannel_B(IPtoS1, S1toIP);
        LocalChannel_A ch_S2_IP = new LocalChannel_A(S2toIP, IPtoS2);
        LocalChannel_B ch_IP_S2 = new LocalChannel_B(IPtoS2, S2toIP);
        LocalChannel_A ch_S3_IP = new LocalChannel_A(S3toIP, IPtoS3);
        LocalChannel_B ch_IP_S3 = new LocalChannel_B(IPtoS3, S3toIP);
        LocalChannel_A ch_S4_IP = new LocalChannel_A(S4toIP, IPtoS4);
        LocalChannel_B ch_IP_S4 = new LocalChannel_B(IPtoS4, S4toIP);
        LocalChannel_A ch_S5_IP = new LocalChannel_A(S5toIP, IPtoS5);
        LocalChannel_B ch_IP_S5 = new LocalChannel_B(IPtoS5, S5toIP);
        LocalChannel_A ch_S6_IP = new LocalChannel_A(S6toIP, IPtoS6);
        LocalChannel_B ch_IP_S6 = new LocalChannel_B(IPtoS6, S6toIP);
        LocalChannel_A ch_S7_IP = new LocalChannel_A(S7toIP, IPtoS7);
        LocalChannel_B ch_IP_S7 = new LocalChannel_B(IPtoS7, S7toIP);


        Runnable runn1 = new Runnable() {
            public void run(){
                try{
                    Client.main( ch_Client_IP, credentials );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn1 ) );

        Runnable runn2 = new Runnable() {
            public void run(){
                try{
                    IP.main( 
                        ch_IP_Client, 
                        ch_IP_Service,
                        ch_IP_S1,
                        ch_IP_S2,
                        ch_IP_S3,
                        ch_IP_S4,
                        ch_IP_S5,
                        ch_IP_S6,
                        ch_IP_S7 );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn2 ) );

        Runnable runn3 = new Runnable() {
            public void run(){
                try{
                    Service.main( ch_Service_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn3 ) );

        Runnable runn4 = new Runnable() {
            public void run(){
                try{
                    S1.main( ch_S1_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn4 ) );

        Runnable runn5 = new Runnable() {
            public void run(){
                try{
                    S2.main( ch_S2_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn5 ) );

        Runnable runn6 = new Runnable() {
            public void run(){
                try{
                    S3.main( ch_S3_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn6 ) );

        Runnable runn7 = new Runnable() {
            public void run(){
                try{
                    S4.main( ch_S4_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn7 ) );

        Runnable runn8 = new Runnable() {
            public void run(){
                try{
                    S5.main( ch_S5_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn8 ) );

        Runnable runn9 = new Runnable() {
            public void run(){
                try{
                    S6.main( ch_S6_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn9 ) );

        Runnable runn10 = new Runnable() {
            public void run(){
                try{
                    S7.main( ch_S7_IP );
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        threads.add( new Thread( runn10 ) );

        return threads;
    }
    
}
