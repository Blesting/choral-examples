package choral.amend.channelsasfields;

import choral.amend.channelsasfields.utils.Client; 
import choral.channels.SymChannel;
import choral.channels.DiDataChannel;
import choral.channels.DiSelectChannel;

enum Signal@R{ SIG }

public class ChannelsAsFields@( First, Second, C ){

	SymChannel@( First, Second )< Object > ch_AB;
	SymChannel@( First, C )< Object > ch_AC;
	DiDataChannel@( First, Second )< Object > diData;
	DiSelectChannel@( First, Second ) diSelect;
	int@First var;
	String@Second var2;
	Object@C var3;

	ChannelsAsFields( SymChannel@( First, Second )< Object > ch_AB, SymChannel@( First, C )< Object > ch_AC ) {
		this.ch_AB = ch_AB;
		this.ch_AC = ch_AC;
	}
    public static void fun( Client@First c_A, Client@Second c_B ) {

        String@First s_A = "A"@First;
		String@Second s_B = s_A;
		int@First i_A = 0@First;
		int@Second i_B = 0@Second;
		c_A.fun0();
		c_A.fun_in( i_A );
		c_A.fun_in( i_B );
		// c_A.fun_in( 0@Second ); // illegal

		c_A.fun_in( c_A.fun_out() );
		c_A.fun_in( c_B.fun_out() );

		c_A.fun_in( c_B.fun_in_out( c_A.fun_out() ) );
		c_A.fun_in( c_B.price.currency );

		helper( i_A, i_B );
		helper( 0@First, 0@Second );
    }

	private void helper(int@First in_A, int@Second in_B){}
}