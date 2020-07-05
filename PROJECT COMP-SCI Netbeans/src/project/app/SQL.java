package project.app;
import java.sql.*;

public class SQL {
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public static ResultSet execute(String query,boolean... open)
    {
        boolean open_flag = (open.length >= 1) ? open[0] : false;
        try 
        {
            //Class.forName("org.sqlite.JDBC");
      
            c = DriverManager.getConnection("jdbc:sqlite:./src/project/resources/PRACTICE.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            /*while ( rs.next() ) {
            /*    String  username = rs.getString("username");
             * String  password = rs.getString("password");  
             * System.out.println( username );
             * System.out.println(password);
             * System.out.println();
            }*/
            
            //stmt.close();
            //c.close();
            //if (ret==0)
            // stmt.close();
            if (open_flag==false)
                c.close();
            return rs;
            //else
            //return null;
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }
    }
    
    public static int updateQuery(String query)
    {
       Connection c = null;
       Statement stmt = null;
       int rs = 0;
       try {
        //Class.forName("org.sqlite.JDBC");
      
       c = DriverManager.getConnection("jdbc:sqlite:./src/project/resources/PRACTICE.db");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");
       stmt = c.createStatement();
       rs = stmt.executeUpdate(query);
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
       stmt.close();
       c.commit();
       c.close();
       return rs;
       //else
       //return null;
       } 
       catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       return 0;
       }
    }


    public static void main( String args[] ) {
       updateQuery("INSERT INTO customer (name,number, address,email) VALUES ('t1','89','t3','t4');");
      // execute("SELECT * FROM login WHERE username='admin' AND password='pass'");
      // System.out.println(customeresult);
      
    }
}