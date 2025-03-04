package choral.examples.distributedauthentication;

import choral.examples.distributedauthentication.AuthResult;
import choral.examples.distributedauthentication.EnumBoolean;
import choral.examples.distributedauthentication.utils.AuthToken;
import choral.examples.distributedauthentication.utils.Base64_Encoder;
import choral.examples.distributedauthentication.utils.ClientRegistry;
import choral.examples.distributedauthentication.utils.Credentials;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import choral.channels.SymChannel;

public class DistAuth@( Client, Service, IP ){
	private SymChannel@( Client, IP )< Object > ch_Client_IP;
	private SymChannel@( Service, IP )< Object > ch_Service_IP;

	public DistAuth(
		SymChannel@( Client, IP )< Object > ch_Client_IP,
		SymChannel@( Service, IP )< Object > ch_Service_IP
	){
		this.ch_Client_IP = ch_Client_IP;
		this.ch_Service_IP = ch_Service_IP;
	}

	private String@Client calcHash( String@Client salt, String@Client pwd ){
        String@Client salt_and_pwd = salt + pwd;
		try {
			MessageDigest@Client md = MessageDigest@Client.getInstance( "SHA3-256"@Client );
			return salt_and_pwd.getBytes( StandardCharsets@Client.UTF_8 )
			>> md::digest
			>> Base64_Encoder@Client::encodeToString;
		} catch ( NoSuchAlgorithmException@Client e ) {
			e.printStackTrace();
			return "Algorithm not found"@Client;
		}
	}

	public AuthResult@( Client, Service ) authenticate( Credentials@Client credentials ) {
		String@Client salt = credentials.username
			>> ch_Client_IP::< String >com
			>> ClientRegistry@IP::getSalt
			>> ch_Client_IP::< String >com;
		Boolean@IP valid = calcHash( salt, credentials.password )
			>> ch_Client_IP::< String >com
			>> ClientRegistry@IP::check;
		if( valid ){
			ch_Client_IP.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_Service_IP.< EnumBoolean >select( EnumBoolean@IP.True );
			AuthToken@IP t = AuthToken@IP.create();
			return new AuthResult@( Client, Service )(
				ch_Client_IP.< AuthToken >com( t ),
				ch_Service_IP.< AuthToken >com( t )
			);
		} else {
			ch_Client_IP.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_Service_IP.< EnumBoolean >select( EnumBoolean@IP.False );
			return new AuthResult@( Client, Service )();
		}
	}
}
