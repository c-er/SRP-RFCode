package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Represents physical Tag objects.
 */
public class Tag implements Comparable<Tag> {
    private String id;
    private String tagid;
    private String taggroupid;
    private String tagtype;
    private boolean lowbattery;
    private int motioncount;
    private boolean motion;
    private ArrayList<TagLink> taglinks;

    /**
     * Constructs the Tag object.
     * @param json the JSON object to be parsed to initialize instance variables
     */
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

    /**
     * Returns the ID of the tag.
     * @return the ID of the tag
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the Tag ID of the tag.
     * @return the Tag ID (same as the ID, with the RFCMII tag identifier stripped)
     */
    public String getTagid() {
        return tagid;
    }

    /**
     * Returns the Tag Group ID that the tag belongs to.
     * @return the Tag Group ID
     */
    public String getTaggroupid() {
        return taggroupid;
    }

    /**
     * Returns the type of the tag.
     * @return the type (model) of tag
     */
    public String getTagtype() {
        return tagtype;
    }

    /**
     * Returns whether the tag is low on battery.
     * @return whether the tag is low on battery or not
     */
    public boolean isLowbattery() {
        return lowbattery;
    }

    /**
     * Returns the amount of times that the tag was recorded as moving.
     * @return the number of movements counted
     */
    public int getMotioncount() {
        return motioncount;
    }

    /**
     * Returns whether or not the tag is in motion.
     * @return whether the tag is in motion or not
     */
    public boolean isMoving() {
        return motion;
    }

    /**
     * Returns the list of all TagLinks that the tag has open.
     * @return an ArrayList of references to TagLink objects that represent the connections it has open
     * @see rfid.rfcode.TagLink
     */
    public ArrayList<TagLink> getTaglinks() {
        return taglinks;
    }

    /**
     * Returns a string that represents the object.
     * @return the string containing data representing the tag
     */
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
                ", taglinks=" + taglinks.size() +
                '}';
    }

    /**
     * Compares two tags based on their ID.
     * @param t the Tag to compare
     * @return a negative integer, zero, or a positive integer as this Tag is less than, equal to, or greater than the specified Tag
     */
    public int compareTo(Tag t)
    {
        return this.getId().compareTo(t.getId());
    }
}
