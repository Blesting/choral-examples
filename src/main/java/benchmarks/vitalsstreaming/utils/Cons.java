package benchmarks.vitalsstreaming.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import choral.examples.vitalsstreaming.utils.Vitals;

public class Cons implements Consumer<Vitals> {
    
    List< Vitals > list = new ArrayList<>();

    @Override
    public void accept(Vitals arg) {
        list.add( arg );
    }

}
