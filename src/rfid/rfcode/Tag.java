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

    public Tag(String id, String tagid, String taggroupid, String tagtype, boolean lowbattery, int motioncount, boolean motion) {
        this.id = id;
        this.tagid = tagid;
        this.taggroupid = taggroupid;
        this.tagtype = tagtype;
        this.lowbattery = lowbattery;
        this.motioncount = motioncount;
        this.motion = motion;
    }
}
