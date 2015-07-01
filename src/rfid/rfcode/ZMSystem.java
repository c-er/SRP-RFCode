package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 7/1/15.
 */
public class ZMSystem {
    private static ZMSystem ourInstance = new ZMSystem();
    private ArrayList<Reader> readers;
    private ArrayList<Tag> tags;

    public static ZMSystem getInstance() {
        return ourInstance;
    }

    private ZMSystem() {
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

    public Reader getReaderById(String id) {
        for(Reader r : readers) {
            if(r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public Tag getTagById(String id) {
        for(Tag t : tags) {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public TagLink getTaglinkById(String tagid, String channelid) {
        for(TagLink tl : getTagById(tagid).getTaglinks()) {
            if(tl.getChannel().getId().equals(channelid)) {
                return tl;
            }
        }
        return null;
    }
}