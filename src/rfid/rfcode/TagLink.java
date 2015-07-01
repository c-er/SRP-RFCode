package rfid.rfcode;

/**
 * Created by sid on 6/30/15.
 */

public class TagLink {
    private Tag tag;
    private Channel channel;
    private int ssi;

    public TagLink(Tag tag, Channel channel, int ssi) {
        this.tag = tag;
        this.channel = channel;
        this.ssi = ssi;
    }
}
