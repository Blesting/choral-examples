package choral.examples.diffiehellman;

import java.math.BigInteger;

public class Config {
    // NETWORK CONFIGURATION

    public static String ALICE_HOSTNAME = System.getProperty("hostname", "localhost");
    public static final int BOB_PORT = 8667;

    // EXAMPLE-SPECIFIC CONFIGURATION

    public static BigInteger SHARED_GENERATOR = new BigInteger("5");
    public static BigInteger SHARED_PRIME = new BigInteger("23");
}