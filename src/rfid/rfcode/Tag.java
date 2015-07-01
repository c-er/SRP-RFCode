package rfid.rfcode;

import org.json.JSONArray;
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
        taglinks = new ArrayList<TagLink>();
        this.id = json.getString("id");
        JSONObject attr = json.getJSONObject("attributes");
        this.tagid = attr.getString("tagid");
        this.taggroupid = attr.getString("taggroupid");
        this.tagtype = attr.getString("tagtype");
        this.lowbattery = attr.getBoolean("lowbattery");
        this.motioncount = attr.getInt("motioncount");
        this.motion = attr.getBoolean("motion");
        /*JSONArray arr = json.getJSONArray("taglinks");
        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            TagLink tl = new TagLink(this, null, obj.getInt("ssi"));
            taglinks.add(tl);
        }*/
    }

    public String getId() {
        return id;
    }

    public String getTagid() {
        return tagid;
    }

    public String getTaggroupid() {
        return taggroupid;
    }

    public String getTagtype() {
        return tagtype;
    }

    public boolean isLowbattery() {
        return lowbattery;
    }

    public int getMotioncount() {
        return motioncount;
    }

    public boolean isMotion() {
        return motion;
    }

    public ArrayList<TagLink> getTaglinks() {
        return taglinks;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", tagid='" + tagid + '\'' +
                ", taggroupid='" + taggroupid + '\'' +
                ", tagtype='" + tagtype + '\'' +
                ", lowbattery=" + lowbattery +
                ", motioncount=" + motioncount +
                ", motion=" + motion +
                ", taglinks=" + taglinks +
                '}';
    }
}
