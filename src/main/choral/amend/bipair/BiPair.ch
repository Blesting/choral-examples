package choral.amend.bipair;

public class BiPair@( A, B )< L@X, R@Y > {
	private L@A left;
	private R@B right;

	public BiPair( L@A left, R@B right ) {
		this.left = left;
		this.right = right;
	}

	public void left() {
		System@A.out.println(this.left);
	}

	public void right() {
		System@B.out.println(this.right);
	}
}
