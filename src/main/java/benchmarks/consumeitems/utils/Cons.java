package benchmarks.consumeitems.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Cons implements Consumer< Integer > {

    List< Integer > list = new ArrayList<>();

    @Override
    public void accept(Integer arg) {
        list.add( arg );
    }

    public List< Integer > list(){
        return list;
    }
    
}
