package choral.examples.distributedauthentication;

import choral.examples.bipair.BiPair;
import choral.examples.distributedauthentication.utils.AuthToken;
import java.util.Optional;

public class AuthResult@( A, B ) extends
   BiPair@( A, B )< Optional< AuthToken >, Optional< AuthToken > > {

   public AuthResult( AuthToken@A t1, AuthToken@B t2 ) {
      super( Optional@A.< AuthToken >of( t1 ), Optional@B.< AuthToken >of( t2 ) );
   }

   public AuthResult(){
      super( Optional@A.< AuthToken >empty(), Optional@B.< AuthToken >empty() );
   }
}
