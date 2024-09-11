package choral.examples.quicksort;

import choral.channels.SymChannel_A;
import choral.channels.SymChannel_B;

import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.Media.SocketByteChannel;
import choral.runtime.serializers.JavaSerializer;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.examples.Quicksort.Quicksort_C;



public class C {
    public static void main(String[] args) throws java.io.IOException {

        SerializerChannel_B channel_B = new SerializerChannel_B( 
            new JavaSerializer(),
            new WrapperByteChannel_B( 
                SocketByteChannel.connect(
                    Config.A_HOSTNAME, Config.BC_PORT
                )
            )
		);
        ServerSocketByteChannel listener_A =
            ServerSocketByteChannel.at( 
                Config.A_HOSTNAME, Config.CA_PORT
        );
        
        SerializerChannel_A channel_A = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    listener_A.getNext()
                )
        );

        Quicksort_C quicksort = new Quicksort_C(channel_B, channel_A);
        quicksort.sort();
        System.out.println("Done at C");
    }
}

// mvn exec:java -Dexec.mainClass="choral.examples.quicksort.C"