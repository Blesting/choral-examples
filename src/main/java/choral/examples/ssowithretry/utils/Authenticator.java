package choral.examples.ssowithretry.utils;

public class Authenticator {
    public boolean valid( Creds x ){
        return Math.random() < 0.3;
    }
}
