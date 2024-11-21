package choral.amend.channelsasfields;

import choral.amend.channelsasfields.utils.Client; 
import choral.channels.SymChannel;
import choral.channels.DiDataChannel;
import choral.channels.DiSelectChannel;

enum Signal@R{ SIG }

public class ChannelsAsFields@( A, B, C ){

	SymChannel@( A, B )< Object > ch_AB;
	SymChannel@( A, C )< Object > ch_AC;
	DiDataChannel@( A, B )< Object > diData;
	DiSelectChannel@( A, B ) diSelect;
	int@A var;
	String@B var2;
	Object@C var3;

	ChannelsAsFields( SymChannel@( A, B )< Object > ch_AB, SymChannel@( A, C )< Object > ch_AC ) {
		this.ch_AB = ch_AB;
		this.ch_AC = ch_AC;
	}
    public static void fun( Client@A c_A, Client@B c_B ) {

        int@A i_A = 0@A;
		int@B i_B = 0@B;
		c_A.fun0();
		c_A.fun_in( i_A );
		c_A.fun_in( i_B );
		// c_A.fun_in( 0@B ); // illegal

		c_A.fun_in( c_A.fun_out() );
		c_A.fun_in( c_B.fun_out() );

		c_A.fun_in( c_B.fun_in_out( c_A.fun_out() ) );
		c_A.fun_in( c_B.price.currency );

		helper( i_A, i_B );
		helper( 0@A, 0@B );
    }

	private void helper(int@A in_A, int@B in_B){}
}