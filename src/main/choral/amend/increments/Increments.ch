package choral.amend.increments;

class Increments@( A, B ) {
	public void fun() {
		int@A a1 = 1@A; 
        int@B b1 = 2@B + 3@B;
        int@A a2 = 5@A + b1;
        a1 += 4@A;
        int@B b2 = a1 + 5@B * b1;
        b2 -= a1;
        // b2 += 1@B + 1@A; // Illegal

        boolean@B b3 = true@B;
        boolean@A a3 = b3 && false@A;
        b3 &= a3 || true@B;
        a3 |= false@A || b3;
        // a3 |= true@B; // illegal
	}
}
