package rfid.rfcode;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */
public class Channel implements Comparable<Channel> {
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

    @Override
    public String toString() {
        return "Channel{" +
                "reader=" + reader.getId() +
                ", id='" + id + '\'' +
                ", taglinks=" + taglinks.size() +
                '}';
    }

    public int compareTo(Channel c){
        return this.getId().compareTo(c.getId());
    }
}