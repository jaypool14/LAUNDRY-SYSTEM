import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class ConnectFebin {
     /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\Febin Jose\\Downloads\\SQLiteStudio\\PRACTICE.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "select * from login";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            //while (rs.next()) {
             System.out.println(rs);
                                   //rs.getString("username") + "\t" +
                                   //rs.getString("login"));
            //}
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var sql = new ConnectFebin();
        sql.selectAll();
    }
}