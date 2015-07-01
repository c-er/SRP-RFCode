package rfid.rfcode;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */

/**
 * Represents the Channel objects that each reader has.
 */
public class Channel implements Comparable<Channel> {
    private Reader reader;
    private String id;
    private ArrayList<TagLink> taglinks;

    /**
     * Constructs the Channel object.
     * @param reader the Reader object that the Channel belongs to
     * @param id the ID of the channel
     * @see rfid.rfcode.Reader
     */
    public Channel(Reader reader, String id) {
        this.reader = reader;
        this.id = id;
        this.taglinks = new ArrayList<TagLink>();
    }

    /**
     * Returns a reference to the reader object that this channel belongs to.
     * @return the reference to the Reader object
     * @see rfid.rfcode.Reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Returns the ID of the channel.
     * @return the ID of the channel
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the list of all TagLinks that the channel has open.
     * @return an ArrayList of references to TagLink objects that represents the connections the channel has open
     * @see rfid.rfcode.TagLink
     */
    public ArrayList<TagLink> getTaglinks() {
        return taglinks;
    }

    /**
     * Returns a string that represents the object.
     * @return the string containing data representing the channel
     */
    @Override
    public String toString() {
        return "Channel{" +
                "reader=" + reader.getId() +
                ", id='" + id + '\'' +
                ", taglinks=" + taglinks.size() +
                '}';
    }

    /**
     * Compares two channels based on their ID.
     * @param c the Channel to compare
     * @return a negative integer, zero, or a positive integer as this Channel is less than, equal to, or greater than the specified Channel
     */
    public int compareTo(Channel c){
        return this.getId().compareTo(c.getId());
    }
}