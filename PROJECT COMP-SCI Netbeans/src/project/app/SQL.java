package project.app;

import java.sql.*;

public class SQL {

    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public static Connection get_conn() {
        try {
            // Get a connection to the sqlite database
            c = DriverManager.getConnection("jdbc:sqlite:./src/project/resources/DB.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        // return the connection for further executing queries
        return c;
    }

    public static ResultSet execute(String query, boolean... open) {
        boolean open_flag = (open.length >= 1) ? open[0] : false;
        try {
            // Get a connection to the sqlite database
            c = DriverManager.getConnection("jdbc:sqlite:./src/project/resources/DB.db");
            // 
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            // Create a SQL statement with the query
            stmt = c.createStatement();
            // Get the Resultset data returned by the query
            rs = stmt.executeQuery(query);
            if (open_flag == false) {
                c.close();
            }
            // Return the Resultset
            return rs;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public static int updateQuery(String query) {
        Connection c = null;
        Statement stmt = null;
        int rs = 0;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:./src/project/resources/DB.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            rs = stmt.executeUpdate(query);
            ResultSet val = stmt.getGeneratedKeys();
            int id = 0;
            while (val.next()) {
                id = val.getInt(1);
                System.out.println(id);
            }
            stmt.close();
            c.commit();
            c.close();
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
    }

    public static void main(String args[]) {
        updateQuery("INSERT INTO customer (name,number, address,email) VALUES ('t1','89','t3','t4');");
    }
}
