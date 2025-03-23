package choral.modified.utils;


public class Pair@R <T@R, S@R> {
	private T@R left;
	private S@R right;

	public Pair( T@R left, S@R right ){
		this.left = left;
		this.right = right;
	}

	public T@R left(){
		return left;
	}

	public S@R right(){
		return right;
	}

}