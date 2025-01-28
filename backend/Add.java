package backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Add
 */
@WebServlet("/add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name;
		Connection conn=null;
		  try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("sucessfull");
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todo", "root", "");
          
        } catch (Exception e) {
            e.printStackTrace();
        }
		JsonObject obj=JsonParser.parseReader(req.getReader()).getAsJsonObject();
		String data=obj.get("data").getAsString();
		 LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	        String formattedDateTime = currentDateTime.format(formatter);
	        System.out.println(formattedDateTime);
	        try {
				PreparedStatement preparedStatement= conn.prepareStatement("insert into information (data,dataid)values(?,?)");
				preparedStatement.setString(2, formattedDateTime);
				preparedStatement.setString(1, data);
				preparedStatement.executeUpdate();
				JsonObject resobj=new JsonObject();
				resobj.addProperty("data",data);
				resobj.addProperty("dataid",formattedDateTime);
				res.setContentType("application/json");
				res.getWriter().write(resobj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	}

}
