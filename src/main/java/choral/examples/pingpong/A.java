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
import choral.examples.pingpong.PingPong_A;

public class A {
    public static void main(String[] args) throws java.io.IOException {
        ServerSocketByteChannel listener =
            ServerSocketByteChannel.at( 
                Config.HOSTNAME, Config.AB_PORT
        );

        SerializerChannel_A channel_AB = new SerializerChannel_A( 
            new JavaSerializer(),
            new WrapperByteChannel_A( 
                listener.getNext()
            )
        );

        SerializerChannel_B channel_BA = new SerializerChannel_B( 
                new JavaSerializer(),
                new WrapperByteChannel_B(
                    SocketByteChannel.connect(
                    Config.HOSTNAME, Config.BA_PORT
                )
            )
        );

        PingPong_A.signal(channel_AB, channel_BA);
        System.out.println("Done at A");
    }
}
// mvn exec:java -Dexec.mainClass="choral.examples.pingpong.A"