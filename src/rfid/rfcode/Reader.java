package rfid.rfcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 6/30/15.
 */
public class Reader {
    private String id;
    private String type;
    private String connectionState;
    private String hostname;
    private Channel channelA;
    private Channel channelB;

    public Reader(JSONObject json) {
        this.id = json.getString("id");
        JSONObject attr = json.getJSONObject("attributes");
        this.type = attr.getString("type");
        this.connectionState = attr.getString("state");
        this.hostname = attr.getString("hostname");
        JSONArray channels = json.getJSONArray("channels");
        channelA = new Channel(this, channels.getString(0));
        channelA = new Channel(this, channels.getString(1));
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getConnectionState() {
        return connectionState;
    }

    public String getHostname() {
        return hostname;
    }

    public Channel getChannelA() {
        return channelA;
    }

    public Channel getChannelB() {
        return channelB;
    }

}
