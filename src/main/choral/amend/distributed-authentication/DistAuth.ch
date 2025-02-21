package choral.amend.distributedauthentication;

import choral.amend.distributedauthentication.AuthResult;
import choral.amend.distributedauthentication.utils.AuthToken;
import choral.amend.distributedauthentication.utils.Base64_Encoder;
import choral.amend.distributedauthentication.utils.ClientRegistry;
import choral.amend.distributedauthentication.utils.Credentials;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import choral.runtime.TLSChannel.TLSChannel;
import choral.amend.distributedauthentication.EnumBoolean;

public class DistAuth@( Client, Service, IP ){
	private TLSChannel@( Client, IP )< Object > ch_Client_IP;
	private TLSChannel@( Service, IP )< Object > ch_Service_IP;

	public DistAuth(
		TLSChannel@( Client, IP )< Object > ch_Client_IP,
		TLSChannel@( Service, IP )< Object > ch_Service_IP
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
			
			>> ClientRegistry@IP::getSalt
			;
		Boolean@IP valid = calcHash( salt, credentials.password )
			
			>> ClientRegistry@IP::check;
		if( valid ){
			
			
			AuthToken@IP t = AuthToken@IP.create();
			return new AuthResult@( Client, Service )(
				                               t,
				                                t
			);
		} else {
			
			
			return new AuthResult@( Client, Service )();
		}
	}
}
