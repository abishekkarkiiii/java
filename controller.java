import java.sql.Statement;
import java.sql.ResultSet;

public class controller {

    int Totalnumber()
    {
        model conn=new model();
        int t=0;
        String queryForTotal="select * from students";
        
        try(Statement stmt = conn.conn().createStatement()){
          ResultSet rs= stmt.executeQuery(queryForTotal);
          while (rs.next()) {
            t=rs.getInt("id");
          }  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return t;
    }
}
