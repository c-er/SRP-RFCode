package rfid.rfcode;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */
public class Reader {
    private String ID;
    private String connectionState;
    private ArrayList<Tag> tagsConnected;
    private Channel channelA;
    private Channel channelB;
}
