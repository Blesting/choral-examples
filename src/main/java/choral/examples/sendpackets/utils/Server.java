package choral.examples.sendpackets.utils;

import java.util.ArrayList;
import java.util.List;


public class Server {
    public List<Integer> file = new ArrayList<>();
	public int n = 0;

	
	public Server(){}
	
	public Server( List<Integer> file ){
		this.file = file;
	}

	public int packets( List<Integer> file ){
        return file.size();
	}

	public Integer mkPacket( List<Integer> file, int n ){
		return file.get(n);
	}

	public List<Integer> readFile( String filename ){
		return file;
	}
}
