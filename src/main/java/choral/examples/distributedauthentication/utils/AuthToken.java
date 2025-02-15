package choral.examples.distributedauthentication.utils;

public class AuthToken {

	String id;
	
	public AuthToken( String id ){
		this.id = id;
	}
	
	public String id(){
		return id;
	}
	
	public static AuthToken create(){
		return new AuthToken( null );
	}
}
