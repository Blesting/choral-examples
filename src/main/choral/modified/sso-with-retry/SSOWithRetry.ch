package choral.modified.ssowithretry;

import choral.channels.SymChannel;
import choral.examples.ssowithretry.utils.*;
import choral.modified.utils.Pair;

import choral.runtime.Serializers.KryoSerializable;

enum Validity_Retry@R{ TOKEN, INVALID, RETRY, ERROR }

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
        Pair@C< Creds, Boolean > msg_C = new Pair@C< Creds, Boolean >( client.creds(), client.again() );
        Pair@CAS< Creds, Boolean > msg_CAS = ch_CASC.< Pair< Creds, Boolean > >com( msg_C );

        Creds@CAS x = msg_CAS.left();
        Boolean@CAS client_again = msg_CAS.right();

        if( authenticator.valid(x) ){
            ch_CASC.< Validity_Retry >select( Validity_Retry@CAS.TOKEN );
            ch_SCAS.< Validity_Retry >select( Validity_Retry@CAS.TOKEN );
            Token@C t = ch_CS.<Token>com( service.newToken() );
        }
        else{
            if( client_again ){
                ch_CASC.< Validity_Retry >select( Validity_Retry@CAS.RETRY );
                ch_SCAS.< Validity_Retry >select( Validity_Retry@CAS.RETRY );
                auth( client, service, authenticator );
            }
            else{
                ch_CASC.< Validity_Retry >select( Validity_Retry@CAS.ERROR );
                ch_SCAS.< Validity_Retry >select( Validity_Retry@CAS.ERROR );
            }
        }
    }
}