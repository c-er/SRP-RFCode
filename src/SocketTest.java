import java.io.*;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws Exception {
        
        Socket sock = new Socket("10.11.34.186", 6501);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        
        try {
            in.readLine();
            in.read();
            in.read();
            out.println("tagcount.xml\r");
        } catch ( IOException ioe ) {
            System.out.println( "IOException: " + ioe.getMessage() + "@ Line 17" );
        }
        
        String str = "";
        while((str = in.readLine()) != null)
        {
            System.out.println(str);
        }
    }
}
