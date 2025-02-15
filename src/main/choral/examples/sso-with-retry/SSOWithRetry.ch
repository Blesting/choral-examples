package choral.examples.ssowithretry;

import choral.channels.SymChannel;
import choral.examples.ssowithretry.utils.*;

import choral.runtime.Serializers.KryoSerializable;

enum Validity@R{ TOKEN, INVALID }
enum Retry@R{ RETRY, ERROR }

public class SSOWithRetry@( C, S, CAS ){
    SymChannel@( C, S )<Object> ch_CS;
    SymChannel@( S, CAS )<Object> ch_SCAS;
    SymChannel@( CAS, C )<Object> ch_CASC;

    public SSOWithRetry(
        SymChannel@( C, S )<Object> ch_CS,
        SymChannel@( S, CAS )<Object> ch_SCAS,
        SymChannel@( CAS, C )<Object> ch_CASC
    ){
        this.ch_CS = ch_CS;
        this.ch_SCAS = ch_SCAS;
        this.ch_CASC = ch_CASC;
    }

    public void auth( Client@C client, Service@S service, Authenticator@CAS authenticator ) {
        Creds@CAS x = ch_CASC.<Creds>com( client.creds() );
        if( authenticator.valid(x) ){
            ch_CASC.< Validity >select( Validity@CAS.TOKEN );
            ch_SCAS.< Validity >select( Validity@CAS.TOKEN );
            Token@C t = ch_CS.<Token>com( service.newToken() );
        }
        else{
            ch_CASC.< Validity >select( Validity@CAS.INVALID );
            ch_SCAS.< Validity >select( Validity@CAS.INVALID );
            if( client.again() ){
                ch_CASC.< Retry >select( Retry@C.RETRY );
                ch_SCAS.< Retry >select( Retry@CAS.RETRY );
                auth( client, service, authenticator );
            }
            else{
                ch_CASC.< Retry >select( Retry@C.ERROR );
                ch_SCAS.< Retry >select( Retry@CAS.ERROR );
            }
        }
    }
}