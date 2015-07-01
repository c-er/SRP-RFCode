package rfid.rfcode;

import org.json.JSONObject;

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

    public Tag(JSONObject json) {
        this.id = json.getString("id");
        JSONObject attr = json.getJSONObject("attributes");
        this.tagid = attr.getString("tagid");
        this.taggroupid = attr.getString("taggroupid");
        this.tagtype = attr.getString("tagtype");
        this.lowbattery = attr.getBoolean("lowbattery");
        this.motioncount = attr.getInt("motioncount");
        this.motion = attr.getBoolean("motion");
    }
}
