package choral.examples.vitalsstreaming.utils;

public class SignatureRegistry {

    public static Boolean isValid( Signature signature ){
        return signature.signature().equals("Doctor");
	}
    
}
