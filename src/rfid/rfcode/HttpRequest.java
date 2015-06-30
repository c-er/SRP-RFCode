package rfid.rfcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

/**
 * Created by ushankar on 6/25/2015.
 */
public class HttpRequest {
    private ArrayList<HttpParameter> params;
    private String command;
    private String host;
    private final String PORT = "6580";
    private final String PATH = "/rfcode_zonemgr/zonemgr/api/";

    public String execute() throws Exception
    {
        String url = "http://" + host + ":" + PORT + PATH + command + "?";

        for(HttpParameter p : params)
        {
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

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        return response.toString();
    }

    public HttpRequest(ArrayList<HttpParameter> params, String command, String host) {
        this.params = params;
        this.command = command;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

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
}
