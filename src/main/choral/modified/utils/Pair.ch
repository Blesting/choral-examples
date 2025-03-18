package choral.modified.utils;


public class Pair@R <T@R> {
	private T@R left;
	private T@R right;

	public Pair( T@R left, T@R right ){
		this.left = left;
		this.right = right;
	}

	public T@R left(){
		return left;
	}

	public T@R right(){
		return right;
	}

}