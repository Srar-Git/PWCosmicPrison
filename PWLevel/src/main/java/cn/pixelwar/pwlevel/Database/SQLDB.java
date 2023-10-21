package cn.pixelwar.pwlevel.Database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLDB {

    public static boolean SQLReloading = false;

    private static Connection connection = null;
    private static String table = "playerleveldata";
    private static String filePath = "plugins/PixelWarLevel/";
    private static String fileName = "PlayerLevel.db";

    public static String getTable() {
        return table;
    }

    public static void reloadConnectionParameters() {
        File databaseName = new File(filePath, fileName);
        if (!databaseName.exists()) {
            try {
                databaseName.createNewFile();
            } catch (IOException ex) {
            }
        }
        if (connection == null) {
            connectToDatabase();
        } else try {
            SQLReloading = true;
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            SignInPluginProperties.sendOperationMessage("Reconnect", placeholders);
            Thread closing = new Thread(() -> {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                }
            });
            closing.start();
            long time = System.currentTimeMillis();
            while (closing.isAlive()) {
                if (System.currentTimeMillis() - time > 10000) {
                    closing.stop();
                    break;
                }
                Thread.sleep(50);
            }
            SQLReloading = false;
            connectToDatabase();
        } catch (Exception ex) {
        }
    }

    public static void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath + fileName);
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "("
                    + " Name VARCHAR(16),"
                    + " totalexp BIGINT,"
                    + " level INT,"
                    + " prestige INT,"
                    + " PRIMARY KEY (Name))").executeUpdate();
        } catch (ClassNotFoundException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
            System.out.println("[PixelWarLevel]未找到class: " + ex.getLocalizedMessage());
        } catch (SQLException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            placeholders.put("{error}", ex.getLocalizedMessage());
            System.out.println("[PixelWarLevel]创建SQLite失败: " + ex.getLocalizedMessage());
        }
    }

    public static void repairConnection() {
        new Thread(() -> {
            int number = 0;
            while (true) {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:" + filePath + fileName);
//                    Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//                    placeholders.put("{database}", "SQLite");
//                    SignInPluginProperties.sendOperationMessage("ConnectionRepair", placeholders);
                    break;
                } catch (SQLException ex) {
                    number++;
                    if (number == -1) {
//                        Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//                        placeholders.put("{database}", "SQLite");
//                        placeholders.put("{number}", String.valueOf(number));
                        System.out.println("[PixelWarLevel]数据库修复失败");
//                        SignInPluginProperties.sendOperationMessage("ConnectionRepairFailure", placeholders);
                    } else {
//                        Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//                        placeholders.put("{database}", "SQLite");
                        System.out.println("[PixelWarLevel]数据库未开始修复");
//                        SignInPluginProperties.sendOperationMessage("BeyondRepair", placeholders);
                        break;
                    }
                }
            }
        }, "SQLiteRepairConnectionThread").start();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void executeUpdate(PreparedStatement statement) {
        while (SQLReloading) {
        }
        try {
            statement.executeUpdate();
        } catch (SQLException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            placeholders.put("{error}", ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : "null");
//            SignInPluginProperties.sendOperationMessage("DataSavingError", placeholders);
            System.out.println("[PixelWarLevel]数据保存错误");
            try {
                if (getConnection().isClosed()) repairConnection();
            } catch (SQLException ex1) {
            }
        }
    }

    public static ResultSet executeQuery(PreparedStatement statement) {
        while (SQLReloading) {
        }
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            placeholders.put("{error}", ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : "null");
//            SignInPluginProperties.sendOperationMessage("DataReadingError", placeholders);
            System.out.println("[PixelWarLevel]数据读取错误");
            try {
                if (getConnection().isClosed()) repairConnection();
            } catch (SQLException ex1) {
            }
        }
        return null;
    }

    @Deprecated
    public static void executeUpdate(String sql) {
        while (SQLReloading) {
        }
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            placeholders.put("{error}", ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : "null");
//            SignInPluginProperties.sendOperationMessage("DataSavingError", placeholders);
            System.out.println("[PixelWarLevel]数据保存错误");
            try {
                if (getConnection().isClosed()) repairConnection();
            } catch (SQLException ex1) {
            }
        }
    }

    @Deprecated
    public static ResultSet executeQuery(String sql) {
        while (SQLReloading) {
        }
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
//            Map<String, String> placeholders = MessageUtil.getDefaultPlaceholders();
//            placeholders.put("{database}", "SQLite");
//            placeholders.put("{error}", ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : "null");
//            SignInPluginProperties.sendOperationMessage("DataReadingError", placeholders);
            System.out.println("[PixelWarLevel]数据读取错误");
            try {
                if (getConnection().isClosed()) repairConnection();
            } catch (SQLException ex1) {
            }
        }
        return null;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String getFilePath() {
        return filePath;
    }

    /**
     * Back up all player data.
     *
     * @param filePath Backup file path.
     * @throws SQLException
     */
    public static void backup(String filePath) throws SQLException {
        try (Connection sqlConnection = DriverManager.getConnection("jdbc:sqlite:" + filePath)) {
            sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS PlayerData("
                    + " Name VARCHAR(16),"
                    + " totalexp BIGINT,"
                    + " level INT,"
                    + " prestige INT,"
                    + " PRIMARY KEY (Name))").executeUpdate();
            if (connection.isClosed()) {
                connectToDatabase();
            }
            ResultSet rs = executeQuery(connection.prepareStatement("SELECT * FROM " + table));
            while (rs.next()) {
                String name = rs.getString("Name");
                long totalexp = rs.getInt("totalexp");
                int level = rs.getInt("level");
                int prestige = rs.getInt("prestige");
                if (name == null) {
                    name = "null";
                }
                PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO PlayerData(Name, totalexp, level, prestige)"
                        + " VALUES(?, ?, ?, ?)");
                statement.setString(1, name);
                statement.setLong(2, totalexp);
                statement.setInt(3, level);
                statement.setInt(4, prestige);
                statement.executeUpdate();
            }
        }
    }


}




