package benchmarks.downloadfile;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.examples.downloadfile.DownloadFile_Storage;
import choral.examples.sendpackets.utils.Server;

public class Storage {

    public static void main(
        LocalChannel_B channel,
        Server server
    ) throws java.io.IOException {
        for( int i = 0; i < Main.ITERATIONS_PER_SIMULATION; i++ ){
            DownloadFile_Storage.downloadFile( channel, server );
        }
        
    }
}