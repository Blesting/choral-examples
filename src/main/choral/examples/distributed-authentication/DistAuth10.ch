package choral.examples.distributedauthentication;

import choral.examples.distributedauthentication.AuthResult;
import choral.examples.distributedauthentication.utils.AuthToken;
import choral.examples.distributedauthentication.utils.Base64_Encoder;
import choral.examples.distributedauthentication.utils.ClientRegistry;
import choral.examples.distributedauthentication.utils.Credentials;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import choral.channels.SymChannel;
import choral.examples.distributedauthentication.EnumBoolean;

public class DistAuth10@( Client, Service, S1, S2, S3, S4, S5, S6, S7, IP ){
	private SymChannel@( Client, IP )< Object > ch_Client_IP;
	private SymChannel@( Service, IP )< Object > ch_Service_IP;
	private SymChannel@( S1, IP )< Object > ch_s1;
	private SymChannel@( S2, IP )< Object > ch_s2;
	private SymChannel@( S3, IP )< Object > ch_s3;
	private SymChannel@( S4, IP )< Object > ch_s4;
	private SymChannel@( S5, IP )< Object > ch_s5;
	private SymChannel@( S6, IP )< Object > ch_s6;
	private SymChannel@( S7, IP )< Object > ch_s7;

	public DistAuth10(
		SymChannel@( Client, IP )< Object > ch_Client_IP,
		SymChannel@( Service, IP )< Object > ch_Service_IP,
		SymChannel@( S1, IP )< Object > ch_s1,
		SymChannel@( S2, IP )< Object > ch_s2,
		SymChannel@( S3, IP )< Object > ch_s3,
		SymChannel@( S4, IP )< Object > ch_s4,
		SymChannel@( S5, IP )< Object > ch_s5,
		SymChannel@( S6, IP )< Object > ch_s6,
		SymChannel@( S7, IP )< Object > ch_s7
	){
		this.ch_Client_IP = ch_Client_IP;
		this.ch_Service_IP = ch_Service_IP;
		this.ch_s1 = ch_s1;
		this.ch_s2 = ch_s2;
		this.ch_s3 = ch_s3;
		this.ch_s4 = ch_s4;
		this.ch_s5 = ch_s5;
		this.ch_s6 = ch_s6;
		this.ch_s7 = ch_s7;
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
			ch_s1.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s2.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s3.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s4.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s5.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s6.< EnumBoolean >select( EnumBoolean@IP.True );
			ch_s7.< EnumBoolean >select( EnumBoolean@IP.True );
			AuthToken@IP t = AuthToken@IP.create();
			return new AuthResult@( Client, Service )(
				ch_Client_IP.< AuthToken >com( t ),
				ch_Service_IP.< AuthToken >com( t )
			);
		} else {
			ch_Client_IP.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_Service_IP.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s1.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s2.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s3.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s4.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s5.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s6.< EnumBoolean >select( EnumBoolean@IP.False );
			ch_s7.< EnumBoolean >select( EnumBoolean@IP.False );
			return new AuthResult@( Client, Service )();
		}
	}
}
