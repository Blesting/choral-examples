package choral.examples.ConsumeItems;

import choral.channels.DiChannel;
import java.util.function.Consumer;
import java.util.Iterator;

public class ConsumeItems@( A, B ) {
	public static void consumeItems( DiChannel@( A, B )< Object > ch, Iterator@A< Integer > it, Consumer@B< Integer > consumer ){
		if ( it.hasNext() ){
			ch.< ConsumeChoice >select( ConsumeChoice@A.AGAIN );
			it.next() >> ch::< Integer > com >> consumer::accept;
			consumeItems( ch, it, consumer );
		} else {
			ch.< ConsumeChoice >select( ConsumeChoice@A.STOP );
		}
    }
}
