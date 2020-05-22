import java.sql.*;

public class SQL {
    public static ResultSet execute(String query)
    {
       Connection c = null;
       Statement stmt = null;
       ResultSet rs = null;
       try {
        //Class.forName("org.sqlite.JDBC");
      
       c = DriverManager.getConnection("jdbc:sqlite:.\\PRACTICE.db");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");
       stmt = c.createStatement();
       rs = stmt.executeQuery(query);
       /*while ( rs.next() ) {
         String  username = rs.getString("username");
         String  password = rs.getString("password");  
         System.out.println( username );
         System.out.println(password);
         System.out.println();
       }*/
       
       //stmt.close();
       //c.close();
       //if (ret==0)
       return rs;
       //else
       //return null;
       } 
       catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
       return null;
       }
    }
    
    public static ResultSet updatequery(String query)
    {
       Connection c = null;
       Statement stmt = null;
       ResultSet rs = null;
       try {
        //Class.forName("org.sqlite.JDBC");
      
       c = DriverManager.getConnection("jdbc:sqlite:.\\PRACTICE.db");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");
       stmt = c.createStatement();
       rs = stmt.executeQuery(query);
       /*while ( rs.next() ) {
         String  username = rs.getString("username");
         String  password = rs.getString("password");  
         System.out.println( username );
         System.out.println(password);
         System.out.println();
       }*/
       
       //stmt.close();
       //c.close();
       //if (ret==0)
       return rs;
       //else
       //return null;
       } 
       catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
       return null;
       }
    }


    public static void main( String args[] ) {
       
       execute("SELECT * FROM login WHERE username='admin' AND password='pass'");
       // System.out.println(customeresult);
      
    }
}