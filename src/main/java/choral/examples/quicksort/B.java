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
import choral.examples.Quicksort.Quicksort_B;



public class B {
    public static void main(String[] args) throws java.io.IOException {
        
        SerializerChannel_B channel_A = new SerializerChannel_B( 
            new JavaSerializer(),
            new WrapperByteChannel_B( 
                SocketByteChannel.connect(
                    Config.A_HOSTNAME, Config.AB_PORT
                )
            )
		);
        ServerSocketByteChannel listener_C =
            ServerSocketByteChannel.at( 
                Config.A_HOSTNAME, Config.BC_PORT
        );
        
        SerializerChannel_A channel_C = new SerializerChannel_A( 
                new JavaSerializer(),
				new WrapperByteChannel_A( 
                    listener_C.getNext()
                )
        );

        Quicksort_B quicksort = new Quicksort_B(channel_A, channel_C);
        quicksort.sort();
        System.out.println("Done at B");
    }
}

// mvn exec:java -Dexec.mainClass="choral.examples.quicksort.B"