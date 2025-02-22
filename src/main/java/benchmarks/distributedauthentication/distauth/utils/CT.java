package benchmarks.distributedauthentication.distauth.utils;

import java.util.ArrayList;
import java.util.List;

import benchmarks.IterationInitializer;
import benchmarks.distributedauthentication.distauth.*;
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
        LocalChannel_A ch_Client_IP = new LocalChannel_A(ClienttoIP, IPtoClient);
        LocalChannel_B ch_IP_Client = new LocalChannel_B(IPtoClient, ClienttoIP);
        LocalChannel_A ch_Service_IP = new LocalChannel_A(ServicetoIP, IPtoService);
        LocalChannel_B ch_IP_Service = new LocalChannel_B(IPtoService, ServicetoIP);


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
                    IP.main( ch_IP_Client, ch_IP_Service );
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

        return threads;
    }
    
}
