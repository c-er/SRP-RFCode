package rfid.rfcode;

/**
 * Created by sid on 6/30/15.
 */

/**
 * Represents an open connection between a Tag object and a Channel object.
 */
public class TagLink implements Comparable<TagLink> {
    private Tag tag;
    private Channel channel;
    private int ssi;

    /**
     * Constructs the TagLink object.
     * @param tag the tag that the link connects
     * @param channel the channel that the link connects to
     * @param ssi the signal strength of the connection
     * @see rfid.rfcode.Tag
     * @see rfid.rfcode.Channel
     */
    public TagLink(Tag tag, Channel channel, int ssi) {
        this.tag = tag;
        this.channel = channel;
        this.ssi = ssi;
    }

    /**
     * Returns a reference to the Tag object that the tag link connects.
     * @return a reference to the Tag object
     * @see rfid.rfcode.Tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Returns a reference to the Channel object that the tag link connects to.
     * @return a reference to the Channel object
     * @see rfid.rfcode.Channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Returns the signal strength of the connection.
     * @return the signal strength of the connection
     */
    public int getSsi() {
        return ssi;
    }

    /**
     * Returns a string that represents the object.
     * @return the string containing data representing the tag link
     */
    @Override
    public String toString() {
        return "TagLink{" +
                "tag=" + tag.getId() +
                ", channel=" + channel.getId() +
                ", ssi=" + ssi +
                '}';
    }

    /**
     * Compares two tag links based on their tag and channel IDs.
     * @param tl the TagLink to compare
     * @return a negative integer, zero, or a positive integer as this TagLink is less than, equal to, or greater than the specified TagLink
     */
    public int compareTo(TagLink tl) {
        return this.getTag().getId().compareTo(tl.getTag().getId()) != 0 ? this.getTag().getId().compareTo(tl.getTag().getId()) : this.getChannel().getId().compareTo(tl.getChannel().getId());
    }
}
