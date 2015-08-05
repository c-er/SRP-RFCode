package rfid.rfcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Represents an HTTP request to be used to access the Zone Manager API.
 */
public class HttpRequest {
    private ArrayList<HttpParameter> params;
    private String command;
    private String host;

    /**
     * Constructs the HTTPRequest object.
     * @param params contains the parameters to be passed
     * @param command contains the command to be executed
     * @param host the hostname (IP address) of the target server
     * @see rfid.rfcode.HttpParameter
     */
    public HttpRequest(ArrayList<HttpParameter> params, String command, String host) {
        this.params = params;
        this.command = command;
        this.host = host;
    }
    /**
     * Constructs the HTTPRequest object without parameters.
     * @param command contains the command to be executed
     * @param host the hostname (IP address) of the target server
     */
    public HttpRequest(String command, String host) {
        this.params = new ArrayList<HttpParameter>();
        this.command = command;
        this.host = host;
    }

    /**
     * Executes this HTTP request.
     * @return the response by the server
     * @throws IOException is thrown when a connection cannot be opened
     * @throws MalformedURLException is thrown when the URL is invalid
     * @throws ProtocolException is thrown when there is a connection protocol error
     */
    // @TODO add timeout on execute (throw an exception?)
    public String execute() throws IOException, MalformedURLException, ProtocolException {
        String url = "http://" + host + ":" + Constants.httpport + Constants.path + command + "?";

        for(HttpParameter p : params) {
            url += p.toString();
        }

        //PrintWriter out = new PrintWriter(new File(outname + "." + outputFormat));

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        if(responseCode != 200) {
            System.err.println("Response Code : " + responseCode);
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        return response.toString();
    }

    /**
     * Returns the command that this HTTP request will execute.
     * @return the command in question
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command that this HTTP request will execute.
     * @param command the value to set the command to
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Returns the list of parameters that this HTTP request uses.
     * @return an ArrayList of references to HttpParameter objects
     */
    public ArrayList<HttpParameter> getParams() {
        return params;
    }

    /**
     * Sets the list of parameters that this HTTP request uses.
     * @param params an ArrayList of HttpParameter objects
     */
    public void setParams(ArrayList<HttpParameter> params) {
        this.params = params;
    }

    /**
     * Returns the hostname of the server this HTTP request targets.
     * @return the hostname (IP address) of the target server
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the hostname of the server this HTTP request will target.
     * @param host the hostname (IP address) of the target server
     */
    public void setHost(String host) {
        this.host = host;
    }
}
