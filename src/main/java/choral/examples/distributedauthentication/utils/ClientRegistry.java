package choral.examples.distributedauthentication.utils;

import java.util.Optional;

public class ClientRegistry {

	public static String getSalt( String username ){
		return "";
	}

	public static Optional< Profile > getProfile( String hash ){
		return Optional.of( new Profile( hash ) );
	}

    public static Boolean check( String hash ){
		return true;
	}
}
