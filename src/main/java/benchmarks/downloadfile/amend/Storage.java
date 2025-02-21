package benchmarks.downloadfile.amend;

import choral.runtime.LocalChannel.LocalChannel_B;
import choral.amend.downloadfile.DownloadFile_Storage;
import choral.amend.sendpackets.utils.Server;

public class Storage {

    public static void main(
        LocalChannel_B channel,
        Server server
    ) throws java.io.IOException {
        for( int i = 0; i < 1000; i++ ){
            DownloadFile_Storage.downloadFile( channel, server );
        }
        
    }
}