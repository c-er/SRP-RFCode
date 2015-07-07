package rfid.rfcode;

public class ZMSystemTest {
    public static void main(String args[])
    {
        ZMSystem sys = new ZMSystem("10.11.34.186");
        while(true)
        {
            for(Tag t : sys.getTags())
            {
                System.out.println(t);
            }
            System.out.printf("\nTags found: %d\n\n", sys.getTags().size());
            for(Reader r : sys.getReaders()){
                System.out.println(r);
            }
            System.out.printf("\nReaders found: %d\n\n", sys.getReaders().size());
            for(Reader r : sys.getReaders()){
                System.out.println(r.getChannelA());
                System.out.println(r.getChannelB());
            }
            System.out.printf("\nChannels found: %d\n\n", sys.getReaders().size()*2);
            int taglinkct = 0;
            for(Tag t : sys.getTags())
            {
                for(TagLink tl : t.getTaglinks()){
                    System.out.println(tl);
                    taglinkct++;
                }
            }
            System.out.printf("\nTagLinks found: %d\n\n", taglinkct);
            System.out.println("Timestamp: " + sys.getTimestamp() + "\n");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sys.update();
        }

    }
}
