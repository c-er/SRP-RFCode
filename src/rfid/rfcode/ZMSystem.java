package rfid.rfcode;

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
            tags.add(new Tag(json.getJSONObject(tagid)));
        }
    }
}
