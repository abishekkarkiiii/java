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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JsonObject obj=JsonParser.parseReader(req.getReader()).getAsJsonObject();
		Connection conn=null;
		  try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("sucessfull");
          conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todo", "root", "");
        
      } catch (Exception e) {
          e.printStackTrace();
      }
		  try {
			PreparedStatement stmt=conn.prepareStatement("delete from information where dataid=?");
			stmt.setString(1, obj.get("del").getAsString());
			if(stmt.executeUpdate()!=0) {
				JsonObject resobj=new JsonObject();
		        resobj.addProperty("sucess","sucess");
		        res.setContentType("application/json");
				res.getWriter().write(resobj.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
