import java.sql.*;

public class SQLiteJDBCFebin {

  public static void main( String args[] ) {

   Connection c = null;
   Statement stmt = null;
   try {
      //Class.forName("org.sqlite.JDBC");
      
      c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Febin Jose\\Downloads\\SQLiteStudio\\PRACTICE.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM login;" );
      
      while ( rs.next() ) {
         String  username = rs.getString("username");
   
         String  password = rs.getString("password");
         
         
         
         System.out.println( username );

         System.out.println(password);
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
  }
}