package rfid.rfcode;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */
public class Tag {
    private String id;
    private String tagid;
    private String taggroupid;
    private String tagtype;
    private boolean lowbattery;
    private int motioncount;
    private boolean motion;
    private ArrayList<TagLink> taglinks;
}
