package choral.examples.downloadfile;

import choral.channels.SymChannel;
import choral.examples.sendpackets.utils.Client;
import choral.examples.sendpackets.utils.Server;

import choral.examples.sendpackets.SendPackets;

public class DownloadFile@( Downloader, Storage ){

    public static Object@Downloader downloadFile( 
        SymChannel@( Downloader, Storage )<Object> channel, 
        String@Downloader filename_D,
        Client@Downloader client,
        Server@Storage server
    ){

        String@Storage filename_S = channel.<String>com( filename_D );
        server.file = server.readFile(filename_S);
        client.file = client.emptyFile();
        server.n = 0@Storage;
        SendPackets@( Downloader, Storage ).sendPackets( channel, server, client );
        return client.file;
    }
}