package choral.amend.ssowithretry.utils;

public class Authenticator {
    public boolean valid( Creds x ){
        return Math.random() < 0.3;
    }
}
