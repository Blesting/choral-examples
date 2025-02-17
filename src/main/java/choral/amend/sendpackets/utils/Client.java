package choral.amend.sendpackets.utils;
import java.util.ArrayList;
import java.util.List;

public class Client {

	public List<Integer> file = new ArrayList<>();

	public List<Integer> append( List<Integer> file, Integer packet ){
        file.add(packet);
		return file;
	}

	public List<Integer> emptyFile(){
		return new ArrayList<>();
	}

}