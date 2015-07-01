package rfid.rfcode;

/**
 * Created by ushankar on 7/1/2015.
 */
public class Tester {
    public static void main(String args[])
    {
        ZMSystem sys = ZMSystem.getInstance();
        for(Tag t : sys.getTags())
        {
            System.out.println(t);
        }
    }
}
