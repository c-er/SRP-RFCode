package rfid.rfcode;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */
public class Channel {
    private Reader reader;
    private String id;
    private ArrayList<TagLink> taglinks;

    public Channel(Reader reader, String id) {
        this.reader = reader;
        this.id = id;
        this.taglinks = new ArrayList<TagLink>();
    }

    public Reader getReader() {
        return reader;
    }

    public String getId() {
        return id;
    }

    public ArrayList<TagLink> getTaglinks() {
        return taglinks;
    }
}
