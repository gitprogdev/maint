package edu.aabu.maintenence.jdbc;

import edu.aabu.util.ServiceLocator;
import edu.aabu.util.ServiceLocatorException;
import java.sql.*;

public class ResourceManager {
    private static String JDBC_DRIVER = "com.ingres.jdbc.IngresDriver";
    private static String JDBC_URL = "jdbc:ingres://localhost:M17/mail::fin4db;CURSOR=readonly;LOOP=on;DATE_FMT=multinational4;ENCODE=ASMO-708";
    private static String JDBC_USER = "webdev";
    private static String JDBC_PASSWORD = "web2007";
    private static Driver driver = null;

    public static synchronized Connection getConnection()
            throws SQLException {
        /*try {
            ServiceLocator sv = ServiceLocator.getInstance();
            return sv.getDBConn(ServiceLocator.FINDB);
        } catch (ServiceLocatorException e) {
            throw new SQLException(e.getMessage());
        }*/
        /*return DriverManager.getConnection(JDBC_URL);*/
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Failed to initialise JDBC driver");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER, JDBC_PASSWORD);
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
