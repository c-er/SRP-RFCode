import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;
import rfid.rfcode.HttpParameter;
import rfid.rfcode.HttpRequest;

import java.util.ArrayList;

/**
 * Created by ushankar on 6/25/2015.
 */
public class HttpTest {

    public static void main(String args[]) throws Exception
    {
        ArrayList<HttpParameter> arr = new ArrayList<HttpParameter>();
        arr.add(new HttpParameter("channelid0", "ARISTOTLE_channel_A"));
        JSONObject json = new JSONObject(new HttpRequest(arr, "taglistbychannel.json", "10.11.34.186").execute());
        for(String s : JSONObject.getNames(json)) System.out.println(s);
    }

    //private static String doCommand

    /*public static final String BASE_URL = "http://10.11.34.186:6580/rfcode_zonemgr/zonemgr/api/";
    private static void doCommand(String command, String outname, String outputFormat) throws Exception {

        String url = BASE_URL + command + "." + outputFormat + "?&tagid0=RFCMII00002408";

        PrintWriter out = new PrintWriter(new File(outname + "." + outputFormat));

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        //print result
        System.out.println(response.toString());
        out.println(response.toString());
        out.flush();

    }

    // default output file type is txt
    private static void doCommand(String command, String outname) throws Exception {

        String url = BASE_URL + command;

        PrintWriter out = new PrintWriter(new File(outname + ".txt"));

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        //print result
        System.out.println(response.toString());
        out.println(response.toString());
        out.flush();

    }*/

}