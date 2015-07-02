package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Maintains ArrayLists of readers and tags through which all components of the RFID hardware can be accessed.
 */
public class ZMSystem {
    private ArrayList<Reader> readers;
    private ArrayList<Tag> tags;

    /**
     * Constructs the ZMSystem master object.
     * @param host the hostname (IP address) of the computer running the Zone Manager server.
     * @see rfid.rfcode.Constants
     */
    public ZMSystem(String host) {
        Constants.host = host;
        readers = new ArrayList<Reader>();
        tags = new ArrayList<Tag>();
        update();
    }

    /**
     * Updates all Tags, Readers, Channels, and TagLinks to the most recently available data.
     * @see rfid.rfcode.Channel
     * @see rfid.rfcode.Tag
     * @see rfid.rfcode.Reader
     * @see rfid.rfcode.TagLink
     */
    public void update() {
        readers.clear();
        tags.clear();
        JSONObject json = null;

        try{
            json = new JSONObject(new HttpRequest("readerlist.json", Constants.host).execute());
        } catch(Exception e) {
            e.printStackTrace();
        }
        for(String readerid : JSONObject.getNames(json)) {
            readers.add(new Reader(json.getJSONObject(readerid)));
        }

        try {
            json = new JSONObject(new HttpRequest("tagprintbygroup.json", Constants.host).execute());
        } catch(Exception e) {
            e.printStackTrace();
        }
        for(String tagid : JSONObject.getNames(json)) {

            Tag t = new Tag(json.getJSONObject(tagid));
            tags.add(t);
            JSONArray arr = json.getJSONObject(tagid).getJSONArray("taglinks");
            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Channel c = getChannelById(obj.getString("channelid"));
                TagLink tl = new TagLink(t, c, obj.getInt("ssi"));
                t.getTaglinks().add(tl);
                c.getTaglinks().add(tl);
            }
        }
    }

    /**
     * Gets the Channel object that corresponds to the ID passed.
     * @param id of the Reader of the channel to return
     * @return the Channel object corresponding to the id passed in
     * @see rfid.rfcode.Channel
     */
    public Channel getChannelById(String id) {
        for(Reader r : readers) {
            if(r.getChannelA().getId().equals(id)) {
                return r.getChannelA();
            }
            if(r.getChannelB().getId().equals(id)) {
                return r.getChannelB();
            }
        }
        return null;
    }

    /**
     * Gets the Reader object that corresponds to the ID passed.
     * @param id of the reader to return
     * @return the Reader object corresponding to the id passed in
     * @see rfid.rfcode.Reader
     */
    public Reader getReaderById(String id) {
        for(Reader r : readers) {
            if(r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Gets the Tag object corresponding to the ID passed.
     * @param id of the tag to return
     * @return the Tag object corresponding to the tagID passed in
     * @see rfid.rfcode.Tag
     */
    public Tag getTagById(String id) {
        for(Tag t : tags) {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Gets the TagLink that connects the Tag and Channel with the given tag ID and channel ID.
     * @param tagid the ID of the tag
     * @param channelid the ID of the channel
     * @return the TagLink object that corresponds to the tagid and channelid is returned.
     * @see rfid.rfcode.TagLink
     */
    public TagLink getTaglinkById(String tagid, String channelid) {
        for(TagLink tl : getTagById(tagid).getTaglinks()) {
            if(tl.getChannel().getId().equals(channelid)) {
                return tl;
            }
        }
        return null;
    }

    /**
     * Returns the current list of Readers.
     * @return an ArrayList of Readers, filled with data as of the most recent call of update()
     * @see rfid.rfcode.Reader
     */
    public ArrayList<Reader> getReaders() { return readers; }

    /**
     * Returns the current list of Tags.
     * @return an ArrayList of Tags, filled with data as of the most recent call of update()
     * @see rfid.rfcode.Tag
     */
    public ArrayList<Tag> getTags() { return tags; }

}