package rfid.rfcode;

/**
 * Created by sid on 6/30/15.
 */

public class TagLink implements Comparable<TagLink> {
    private Tag tag;
    private Channel channel;
    private int ssi;

    public TagLink(Tag tag, Channel channel, int ssi) {
        this.tag = tag;
        this.channel = channel;
        this.ssi = ssi;
    }

    public Tag getTag() {
        return tag;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getSsi() {
        return ssi;
    }

    @Override
    public String toString() {
        return "TagLink{" +
                "tag=" + tag.getId() +
                ", channel=" + channel.getId() +
                ", ssi=" + ssi +
                '}';
    }

    public int compareTo(TagLink tl)
    {
        return this.getTag().getId().compareTo(tl.getTag().getId()) != 0 ? this.getTag().getId().compareTo(tl.getTag().getId()) : this.getChannel().getId().compareTo(tl.getChannel().getId());
    }
}
