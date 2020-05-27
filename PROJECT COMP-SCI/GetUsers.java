import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
public class GetUsers
{
    public static String[] getusers_list(String text)
    {
        SQL sql = new SQL();
        String[] user_list = {};
        String check_query = String.format("SELECT * FROM customer WHERE name LIKE '%%%s%%' OR email LIKE '%%%s%%';", text, text);
        try
        {
            System.out.println(check_query);
            ResultSet rs =sql.execute(check_query, true);
            ArrayList<String> user_array_list = new ArrayList<>();
            while (rs.next()) {
                String  username = rs.getString("name");
                String  email = rs.getString("email");  
                System.out.println(username);
                System.out.println(email);
                user_array_list.add(username+" : "+email);
            }
            user_list = user_array_list.toArray(new String[user_array_list.size()]);
            
            SQL.c.close();
        }
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //return false;
        }
        return user_list;
    }
    public static void main(String[] args) 
    {
        System.out.println(Arrays.toString(getusers_list("aa")));
    }
}
