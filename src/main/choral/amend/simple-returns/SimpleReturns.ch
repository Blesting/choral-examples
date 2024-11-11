package choral.amend.simpleretruns;

class SimpleReturns@( A, B ) {
	public void fun() {
		int@A i_A = 0@A;
		int@B i_B = 0@B;
        
        int@A i2_A = out_A(1@A);
        i2_A = out_A(i_A);
        i2_A = out_A(i_B);
        // i2_A = out_A(1@B); // illegal

        int@B i2_B = out_A(1@A);
        i2_B = out_A(i_A);
        i2_B = out_A(i_B);
        // i2_B = out_A(1@B); // illegal
        
	}

	private int@A out_A(int@A in_A){
        return in_A;
    }

}
