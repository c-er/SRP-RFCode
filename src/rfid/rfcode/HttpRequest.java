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
 * Created by ushankar on 6/25/2015.
 */
public class HttpRequest {
    private ArrayList<HttpParameter> params;
    private String command;
    private String host;

    // constuctors
    /*
        Used to form the URL which outputs a string in 3 possible formats
     */
    public HttpRequest(ArrayList<HttpParameter> params, String command, String host) {
        this.params = params;
        this.command = command;
        this.host = host;
    }

    public HttpRequest(String command, String host) {
        this.params = new ArrayList<HttpParameter>();
        this.command = command;
        this.host = host;
    }

    /**
     * @return the response by the server when passing in a command specified by instance variable "command"
     * and passing in params if necessary with the ArrayList instance variable
     * @throws IOException
     * @throws MalformedURLException    is thrown if argument to URL constructor is null
     * @throws ProtocolException
     */
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

    // getters and setters
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<HttpParameter> getParams() {
        return params;
    }

    public void setParams(ArrayList<HttpParameter> params) {
        this.params = params;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
