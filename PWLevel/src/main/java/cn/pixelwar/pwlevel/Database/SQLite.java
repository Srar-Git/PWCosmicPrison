package cn.pixelwar.pwlevel.Database;//package cn.pixelwar.pixelwarlevel.Database;
//
//import jdk.nashorn.internal.objects.annotations.Getter;
//import org.bukkit.entity.Player;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SQLite {
//
//    public static final Map<String, SQLite> cache = new HashMap();
//
//    @Getter
//    private int continuous = 0;
//    @Getter
//    private int year = 1970;
//
//
//    private final String playerName;
//
//    public SQLite(Player player) {
//        playerName = player.getDisplayName();
//
//        try {
//            if (SQLDB.getConnection().isClosed()) {
//                return;
//            }
//        } catch (SQLException ex) {}
//
//        register(uuid);
//        try {
//            ResultSet rs = SQLDB.executeQuery(SQLDB.getConnection().prepareStatement("SELECT * FROM " + SQLDB.getTable() + " WHERE UUID = '" + uuid + "'"));
//            if (rs.next()) {
//                continuous = rs.getObject("Continuous") != null ? rs.getInt("Continuous") : 0;
//                name = rs.getObject("Name") != null ? rs.getString("Name") : null;
//                year = rs.getObject("Year") != null ? rs.getInt("Year") : 1970;
//                month = rs.getObject("Month") != null ? rs.getInt("Month") : 1;
//                day = rs.getObject("Day") != null ? rs.getInt("Day") : 1;
//                hour = rs.getObject("Hour") != null ? rs.getInt("Hour") : 0;
//                minute = rs.getObject("Minute") != null ? rs.getInt("Minute") : 0;
//                second = rs.getObject("Second") != null ? rs.getInt("Second") : 0;
//                retroactiveCard = rs.getObject("RetroactiveCard") != null ? rs.getInt("RetroactiveCard") : 0;
//                if (rs.getObject("History") != null && !rs.getString("History").equals("")) {
//                    List<SignInDate> list = new ArrayList();
//                    for (String data : Arrays.asList(rs.getString("History").split(", "))) {
//                        list.add(SignInDate.getInstance(data));
//                    }
//                    history = list;
//                } else {
//                    history = new ArrayList();
//                }
//            }
//            checkContinuousSignIn();
//        } catch (SQLException ex) {
//            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//
//
//}
