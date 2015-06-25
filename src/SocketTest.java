import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by uday on 6/25/15.
 */
public class SocketTest {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("10.11.34.186", 6501);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        in.readLine();
        in.read();
        in.read();
        out.println("taglist.xml\r");
        String str = "";
        while((str = in.readLine()) != null)
        {
            System.out.println(str);
        }
    }
}