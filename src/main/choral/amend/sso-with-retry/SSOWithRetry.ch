package choral.amend.ssowithretry;

import choral.channels.SymChannel;
import choral.amend.ssowithretry.utils.Client;
import choral.amend.ssowithretry.utils.Service;
import choral.amend.ssowithretry.utils.Authenticator;

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
        Object@CAS x =                      client.creds();
        if( true@CAS ){
            

            Object@C t =                    service.newToken();
        }
        else{
            

            if( client.again() ){
                

                auth( client, service, authenticator );
            }
            else{
                

            }
        }
    }
}