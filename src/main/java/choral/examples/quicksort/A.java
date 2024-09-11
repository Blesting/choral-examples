package choral.examples.quicksort;

import choral.channels.SymChannel_A;
import choral.channels.SymChannel_B;
import java.util.List;

import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.Media.SocketByteChannel;
import choral.runtime.serializers.JavaSerializer;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.examples.Quicksort.Quicksort_A;



public class A {
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

        Quicksort_A quicksort = new Quicksort_A(channel_B, channel_C);
        List<Integer> sortedList = quicksort.sort(Config.LIST);
        System.out.println("result from A: " + sortedList);
    }
}

// mvn exec:java -Dexec.mainClass="choral.examples.quicksort.A"