package benchmarks.downloadfile.amend;

import choral.runtime.LocalChannel.LocalChannel_A;
import choral.amend.downloadfile.DownloadFile_Downloader;
import choral.amend.sendpackets.utils.Client;

public class Downloader {

    public static void main(
        LocalChannel_A channel,
        String filename,
        Client client
    ) throws java.io.IOException {
        for( int i = 0; i < 1000; i++ ){
            DownloadFile_Downloader.downloadFile( channel, filename, client );
        }
    }
}