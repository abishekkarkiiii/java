import java.sql.Connection;
import java.sql.DriverManager;

/**
 * model
 */
public class model {
       
    Connection conn()
    {
        try {
            Class.forName("com.mysql.cj.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

         try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abi", "root", "");
           return  conn;
         } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
         }
        
    }
    
}