package rfid.rfcode;

/**
 * Holds important constants to be used between classes.
 */
public final class Constants {
    /**
     * The hostname (IP address) of the computer running the Zone Manager software.
     */
    public static String host;
    /**
     * The port that the Zone Manager server listens to for HTTP requests.
     */
    public static String httpport = "6580";
    /**
     * The base URL used to access the Zone Manager API.
     */
    public static String path = "/rfcode_zonemgr/zonemgr/api/";

    private Constants() {}
}
