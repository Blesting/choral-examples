package choral.amend.SimpleArithmetic;

class SimpleArithmetic@( A, B ) {
	public void calc() {
		int@A a1 = 1@A; 
        int@B b1 = 2@B + 3@B;
        int@A a2 = 4@A + b1;
        int@B b2 = a1 + 5@B - b1 * a2;
        int@B b3 = 1@B + 1@A; // Illegal
	}
}
