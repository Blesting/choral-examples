package choral.examples.mergesort;

import choral.channels.SymChannel_A;
import choral.channels.SymChannel_B;
import java.util.List;

import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.Media.SocketByteChannel;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.runtime.Serializers.JavaSerializer;
import choral.examples.Mergesort.Mergesort_A;



public class A {
    
    public static void main( List<Integer> input ) throws java.io.IOException {
        ServerSocketByteChannel listener_B =
            ServerSocketByteChannel.at( 
                Config.A_HOSTNAME, Config.AB_PORT
        );

        SerializerChannel_A channel_B = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    listener_B.getNext()
                )
        );
        SerializerChannel_B channel_C = new SerializerChannel_B( 
                new JavaSerializer(),
				new WrapperByteChannel_B(
                    SocketByteChannel.connect(
                    Config.A_HOSTNAME, Config.CA_PORT
                )
            )
        );

        Mergesort_A mergesort = new Mergesort_A(channel_B, channel_C);
        List<Integer> sortedList = mergesort.sort(input);
        listener_B.close();
        System.out.println("result from A: " + sortedList);
    }

    
    public static void main(String[] args) throws java.io.IOException {
        
        ServerSocketByteChannel listener_B =
            ServerSocketByteChannel.at( 
                Config.A_HOSTNAME, Config.AB_PORT
        );

        SerializerChannel_A channel_B = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    listener_B.getNext()
                )
        );
        SerializerChannel_B channel_C = new SerializerChannel_B( 
                new JavaSerializer(),
				new WrapperByteChannel_B(
                    SocketByteChannel.connect(
                    Config.A_HOSTNAME, Config.CA_PORT
                )
            )
        );

        Mergesort_A mergesort = new Mergesort_A(channel_B, channel_C);
        List<Integer> sortedList = mergesort.sort(Config.LIST);
        listener_B.close();
        System.out.println("result from A: " + sortedList);
    }
}
