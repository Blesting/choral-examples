package choral.examples.pingpong;

import choral.channels.DiSelectChannel_B;
import choral.channels.DiSelectChannel_A;

import choral.runtime.Media.ServerSocketByteChannel;
import choral.runtime.Media.SocketByteChannel;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_A;
import choral.runtime.WrapperByteChannel.WrapperByteChannel_B;
import choral.runtime.SerializerChannel.SerializerChannel_A;
import choral.runtime.SerializerChannel.SerializerChannel_B;
import choral.runtime.Serializers.JavaSerializer;
import choral.examples.pingpong.PingPong_B;

public class B {
    public static void main(String[] args) throws java.io.IOException {
        SerializerChannel_B channel_AB = new SerializerChannel_B( 
                new JavaSerializer(),
                new WrapperByteChannel_B(
                    SocketByteChannel.connect(
                    Config.HOSTNAME, Config.AB_PORT
                )
            )
        );
        ServerSocketByteChannel listener =
            ServerSocketByteChannel.at( 
                Config.HOSTNAME, Config.BA_PORT
        );

        SerializerChannel_A channel_BA = new SerializerChannel_A( 
            new JavaSerializer(),
            new WrapperByteChannel_A( 
                listener.getNext()
            )
        );
        PingPong_B.signal(channel_AB, channel_BA);
        System.out.println("Done at B");
    }
}
// mvn exec:java -Dexec.mainClass="choral.examples.pingpong.B"