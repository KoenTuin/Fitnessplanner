/*
Author: Koen van der Tuin
Author: Youri Pellicaan

Purpose: The purpose of the Database class is to create a connection with our used database and to also create
an instance of that database so it can be used throughout the application where needed.
 */
package database;

import java.sql.*;
import java.util.Enumeration;

public class Database {

    private static final String DB_DEFAULT_DATABASE = "fitness_database";
    private static final String DB_DEFAULT_SERVER_URL = "localhost";
    private static final String DB_DEFAULT_ACCOUNT = "root";
    private static final String DB_DEFAULT_PASSWORD = "root";

    private final static String DB_DRIVER_URL = "com.mysql.jdbc.Driver";
    private final static String DB_DRIVER_PREFIX = "jdbc:mysql://";
    private final static String DB_DRIVER_PARAMETERS = "?useSSL=false";

    private Connection connection = null;

    // set for verbose logging of all queries
    private boolean verbose = true;

    // remembers the first error message on the connection
    private String errorMessage = null;
    public static Database database;

    //Singleton
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    // constructors
    private Database() {
        this(DB_DEFAULT_DATABASE, DB_DEFAULT_SERVER_URL, DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
    }

    private Database(String dbName, String serverURL, String account, String password) {
        try {
            // verify that a proper JDBC driver has been installed and linked
            if (!selectDriver(DB_DRIVER_URL)) {
                return;
            }

            if (password == null) {
                password = "";
            }

            // establish a connection to a named database on a specified server
            String connStr = DB_DRIVER_PREFIX + serverURL + "/" + dbName + DB_DRIVER_PARAMETERS;
            log("Connecting " + connStr);
            this.connection = DriverManager.getConnection(connStr, account, password);

        } catch (SQLException eSQL) {
            error(eSQL);
            this.close();
        }
    }

    public final void close() {

        if (this.connection == null) {
            // db has been closed earlier already
            return;
        }
//        try {
//            this.connection.close();
//            this.connection = null;
//            this.log("Data base has been closed");
//        } catch (SQLException eSQL) {
//            error(eSQL);
//        }
    }

    /***
     * elects proper loading of the named driver for database connections.
     * This is relevant if there are multiple drivers installed that match the JDBC type
     * @param driverName    the name of the driver to be activated.
     * @return indicates whether a suitable driver is available
     */
    private Boolean selectDriver(String driverName) {
        try {
            Class.forName(driverName);
            // Put all non-prefered drivers to the end, such that driver selection hits the first
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (!d.getClass().getName().equals(driverName)) {   // move the driver to the end of the list
                    DriverManager.deregisterDriver(d);
                    DriverManager.registerDriver(d);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            error(ex);
            return false;
        }
        return true;
    }

    /***
     * Executes an SQL query that yields a ResultSet with the outcome of the
     * query. This outcome may be a single row with a single column in case of
     * a scalar outcome.
     * @param sql   the full sql text of the query.
     * @return a ResultSet object that can iterate along all rows
     * @throws SQLException
     */
    public ResultSet executeResultSetQuery(String sql) throws SQLException {
        Statement s = this.connection.createStatement();
        log(sql);
        ResultSet rs = s.executeQuery(sql);
        // cannot close the statement, because that also closes the resultset
        return rs;
    }

    /***
     * echoes a message on the system console, if run in verbose mode
     * @param message
     */
    public void log(String message) {
        if (isVerbose()) {
            System.out.println("Database: " + message);
        }
    }

    /***
     * echoes an exception and its stack trace on the system console.
     * remembers the message of the first error that occurs for later reference.
     * closes the connection such that no further operations are possible.
     * @param e
     */
    public final void error(Exception e) {
        String msg = "Database-" + e.getClass().getName() + ": " + e.getMessage();

        // capture the message of the first error of the connection
        if (this.errorMessage == null) {
            this.errorMessage = msg;
        }
        System.out.println(msg);
        e.printStackTrace();

        // if an error occurred, close the connection to prevent further operations
        this.close();
    }

    public boolean isVerbose() {
        return verbose;
    }
}
