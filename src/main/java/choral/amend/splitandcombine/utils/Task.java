package choral.amend.splitandcombine.utils;

import java.util.List;

public class Task {
    
    List< Integer > list;

    public Task( List< Integer > list ){
        this.list = list;
    }

    public List< Integer > list(){
        return list;
    }
    
    public Result run(){
        Integer sum = 0;
        for( Integer i : list ){
            sum += i;
        }
        return new Result( sum );
    }
}
