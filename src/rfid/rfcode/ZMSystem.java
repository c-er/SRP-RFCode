package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sid on 7/1/15.
 */
public class ZMSystem {
    private static ZMSystem ourInstance = new ZMSystem();
    private ArrayList<Reader> readers;
    private ArrayList<Tag> tags;

    /*
        Constructor is private to avoid multiple constructions of the same object (Singleton pattern)
     */
    private ZMSystem() {
        readers = new ArrayList<Reader>();
        tags = new ArrayList<Tag>();
        update();
    }

    /**
     * @throws IOException, MalformedURLException, ProtocolException    -- see HttpRequest.execute()
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
     * @param id of the Reader of the channel to return
     * @return the Channel object corresponding to the id passed in
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
     * @param id of the reader to return
     * @return the Reader object corresponding to the id passed in
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
     * @param id of the tag to return
     * @return the Tag object corresponding to the tagID passed in
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
     * @param tagid and channelid are passed in together. The link between the tag and channel is known as
     *          a TagLink...
     * @return the TagLink object that corresponds to the tagid and channelid is returned.
     */
    public TagLink getTaglinkById(String tagid, String channelid) {
        for(TagLink tl : getTagById(tagid).getTaglinks()) {
            if(tl.getChannel().getId().equals(channelid)) {
                return tl;
            }
        }
        return null;
    }

    // getters
    public ArrayList<Reader> getReaders() { return readers; }
    public ArrayList<Tag> getTags() { return tags; }
    public static ZMSystem getInstance() { return ourInstance; }

}