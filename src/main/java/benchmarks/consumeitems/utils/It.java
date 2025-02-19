package benchmarks.consumeitems.utils;

import java.util.Iterator;
import java.util.List;

public class It implements Iterator< Integer > {

    List< Integer > list;

    public It( List< Integer > list ){
        this.list = list;
    }

    public List< Integer > list(){
        return list;
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.removeFirst();
    }
    
    

}
