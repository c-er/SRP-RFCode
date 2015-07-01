package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */

/**
 * Represents physical Reader objects.
 */
public class Reader implements Comparable<Reader> {
    private String id;
    private String type;
    private String connectionState;
    private String hostname;
    private Channel channelA;
    private Channel channelB;

    /**
     * Constructs the Reader object.
     * @param json the JSON object to be parsed to initialize the instance variables
     */
    public Reader(JSONObject json) {
        this.id = json.getString("id");
        JSONObject attr = json.getJSONObject("attributes");
        this.type = attr.getString("type");
        this.connectionState = attr.getString("state");
        this.hostname = attr.getString("hostname");
        JSONArray channels = attr.getJSONArray("channels");
        channelA = new Channel(this, channels.getString(0));
        channelB = new Channel(this, channels.getString(1));
    }

    /**
     * Returns the ID of the reader.
     * @return the ID of the reader
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the type of the reader.
     * @return the type (model) of the reader
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the connection state of the reader.
     * @return the connection state of the reader
     */
    public String getConnectionState() {
        return connectionState;
    }

    /**
     * Returns the hostname of the reader.
     * @return the hostname (IP address) of the reader
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns a reference to the Channel object representing channel A of the reader.
     * @return a reference to the Channel object specified.
     * @see rfid.rfcode.Channel
     */
    public Channel getChannelA() {
        return channelA;
    }

    /**
     * Returns a reference to the Channel object representing channel B of the reader.
     * @return a reference to the Channel object specified.
     * @see rfid.rfcode.Channel
     */
    public Channel getChannelB() {
        return channelB;
    }

    /**
     * Returns a string that represents the object.
     * @return the string containing data representing the reader
     */
    @Override
    public String toString() {
        return "Reader{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", connectionState='" + connectionState + '\'' +
                ", hostname='" + hostname + '\'' +
                ", channelA=" + channelA.getId() +
                ", channelB=" + channelB.getId() +
                '}';
    }

    /**
     * Compares two readers based on their ID.
     * @param r the Reader to compare
     * @return a negative integer, zero, or a positive integer as this Reader is less than, equal to, or greater than the specified Reader
     */
    public int compareTo(Reader r) {
        return this.getId().compareTo(r.getId());
    }

}
