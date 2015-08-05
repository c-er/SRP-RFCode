package rfid.rfcode;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ZMSystemDatabaseTest {
    public static void main(String args[]) {
        ZMSystem sys = new ZMSystem("10.11.34.186");
        Connection jdbc = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            jdbc = DriverManager.getConnection("jdbc:mysql://10.11.34.174:3306/shelter_mac", "root", "PLATO");
            int i = 0;
            while(true) {
                sys.update();
                ZMSystemDatabaseTest.updateDB(sys, jdbc);
                Thread.sleep(2000);
                System.out.println("Updated Database. Pass: "+(++i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
    public static void updateOrAddReaders(ZMSystem sys, Connection jdbc) throws SQLException {
        PreparedStatement getReader = jdbc.prepareStatement("SELECT * FROM readers WHERE readerid=? AND readervendor = \"rfcode\"");
        PreparedStatement insertReader = jdbc.prepareStatement("INSERT INTO readers VALUES (?,\"rfcode\",?,?,?)");
        PreparedStatement updateReader = jdbc.prepareStatement("UPDATE readers SET hostname=?, readertype=?, connectstatus=? WHERE readerid=? AND readervendor=\"rfcode\"");
        for(Reader r : sys.getReaders()){
            getReader.setString(1, r.getId());
            if(!getReader.executeQuery().next()) {
                insertReader.setString(1, r.getId());
                insertReader.setString(2, r.getType());
                insertReader.setString(3, r.getHostname());
                insertReader.setString(4, r.getConnectionState());
                insertReader.executeUpdate();
            } else {
                updateReader.setString(1, r.getHostname());
                updateReader.setString(2, r.getType());
                updateReader.setString(3, r.getConnectionState());
                updateReader.setString(4, r.getId());
                updateReader.executeUpdate();
            }
        }
    }
    public static void updateOrAddChannels(ZMSystem sys, Connection jdbc) throws SQLException {
        PreparedStatement getChannel = jdbc.prepareStatement("SELECT * FROM channels WHERE channelid=? AND channelvendor = \"rfcode\"");
        PreparedStatement insertChannel = jdbc.prepareStatement("INSERT INTO channels VALUES (?,\"rfcode\",?,\"rfcode\", NULL)");
        PreparedStatement updateChannel = jdbc.prepareStatement("UPDATE channels SET readerid=?, readervendor=\"rfcode\" WHERE channelid=? AND channelvendor=\"rfcode\"");
        for(Reader r : sys.getReaders()){
            Channel a = r.getChannelA();
            Channel b = r.getChannelB();
            getChannel.setString(1, a.getId());
            if(!getChannel.executeQuery().next()) {
                insertChannel.setString(1, a.getId());
                insertChannel.setString(2, r.getId());
                insertChannel.executeUpdate();
            } else {
                updateChannel.setString(1, r.getId());
                updateChannel.setString(2, a.getId());
                updateChannel.executeUpdate();
            }
            getChannel.setString(1, b.getId());
            if(!getChannel.executeQuery().next()) {
                insertChannel.setString(1, b.getId());
                insertChannel.setString(2, r.getId());
                insertChannel.executeUpdate();
            } else {
                updateChannel.setString(1, r.getId());
                updateChannel.setString(2, b.getId());
                updateChannel.executeUpdate();
            }
        }
    }
    public static void addTagAndReads(ZMSystem sys, Connection jdbc) throws SQLException {
        PreparedStatement getTag = jdbc.prepareStatement("SELECT * FROM tags WHERE tagid=? AND tagvendor = \"rfcode\"");
        PreparedStatement insertTag = jdbc.prepareStatement("INSERT INTO tags VALUES (?,\"rfcode\")");
        PreparedStatement getTagRead = jdbc.prepareStatement("SELECT * FROM tagreads WHERE tagid=? AND tagvendor = \"rfcode\" AND channelid=? AND channelvendor=\"rfcode\" AND readtime=?");
        PreparedStatement insertTagRead = jdbc.prepareStatement("INSERT INTO tagreads VALUES (?,\"rfcode\",?,\"rfcode\",?,?)");
        for(Tag t : sys.getTags()){
            getTag.setString(1, t.getId());
            if(!getTag.executeQuery().next()) {
                insertTag.setString(1, t.getId());
                insertTag.executeUpdate();
            }
            for(TagLink tl : t.getTaglinks()){
                Timestamp time = new Timestamp(sys.getTimestamp());
                getTagRead.setString(1, t.getId());
                getTagRead.setString(2, tl.getChannel().getId());
                getTagRead.setTimestamp(3, time);
                if(!getTagRead.executeQuery().next()){
                    insertTagRead.setString(1, t.getId());
                    insertTagRead.setString(2, tl.getChannel().getId());
                    insertTagRead.setTimestamp(3, time);
                    insertTagRead.setInt(4, tl.getSsi());
                    insertTagRead.executeUpdate();
                }
            }
        }
    }
    public static ArrayList<HashMap<String, String>> getSsiData(Connection jdbc) throws SQLException {
        PreparedStatement setTime = jdbc.prepareStatement("SET @t=UNIX_TIMESTAMP()");
        PreparedStatement getLatestReads = jdbc.prepareStatement("SELECT * FROM tagreads WHERE abs(UNIX_TIMESTAMP(readtime)-@t)=(\n" +
                "    SELECT MIN(abs(@t-UNIX_TIMESTAMP(readtime))) FROM tagreads\n" +
                ");");
        setTime.execute();
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        ResultSet reads = getLatestReads.executeQuery();
        while(reads.next()){
            HashMap<String, String> indread = new HashMap<String, String>();
            indread.put("tagid",reads.getString("tagid"));
            indread.put("tagvendor",reads.getString("tagvendor"));
            indread.put("channelid",reads.getString("channelid"));
            indread.put("channelvendor",reads.getString("channelvendor"));
            indread.put("readtime",reads.getTimestamp("readtime").toString());
            indread.put("ssi",String.valueOf(reads.getInt("ssi")));
            data.add(indread);
        }
        return data;
    }
    public static void addLocationData(Connection jdbc, String tagid, String tagvendor, String timestampstring, double x, double y) throws SQLException {
        PreparedStatement addLocation = jdbc.prepareStatement("INSERT INTO locations VALUES (?,?,?,POINT(?,?))");
        addLocation.setString(1, tagid);
        addLocation.setString(2, tagvendor);
        addLocation.setTimestamp(3, Timestamp.valueOf(timestampstring));
        addLocation.setDouble(4, x);
        addLocation.setDouble(5, y);
        addLocation.executeUpdate();
    }
    public static void updateDB(ZMSystem sys, Connection jdbc) throws SQLException {
        ZMSystemDatabaseTest.updateOrAddReaders(sys, jdbc);
        ZMSystemDatabaseTest.updateOrAddChannels(sys, jdbc);
        ZMSystemDatabaseTest.addTagAndReads(sys, jdbc);
    }
}
