import java.sql.*;

public class SQL {
    public static void execute(String query, int ret)
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
       if (ret==0)
       rs = stmt.executeQuery(query);
       else
       stmt.executeQuery(query);
      
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
       //return rs;
       //else
       //return null;
       } 
       catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
       //return null;
       }
    }

    public static void main( String args[] ) {
       
       execute("INSERT INTO customer (name,number, address,email) VALUES ('s','22','s3','s4')", 1    );
       // System.out.println(customeresult);
      
    }
}