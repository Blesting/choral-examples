package choral.examples.mergesort;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    // NETWORK CONFIGURATION

    public static String A_HOSTNAME = System.getProperty("hostname", "localhost");
    public static final int AB_PORT = 8667;
    public static final int BC_PORT = 8668;
    public static final int CA_PORT = 8669;


    // EXAMPLE-SPECIFIC CONFIGURATION
    public static ArrayList<Integer> LIST = new ArrayList<Integer>(Arrays.asList(6, -10, 16, 3, 80, 1, 2, 100, 3, 2, 9, 11, 10));
}
