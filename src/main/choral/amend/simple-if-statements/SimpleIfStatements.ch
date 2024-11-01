package choral.amend.SimpleIfStatements;

class SimpleIfStatements@( A, B ) {
	public void fun() {
		int@A c_A = 0@A;
        int@B c_B = 0@B;
        
        if( 1@A < 4@A ){}

        if( 1@A > c_A ){}

        if( 0@A > c_B ){} 

        if( c_B > 0@A ){} 

	}
}
