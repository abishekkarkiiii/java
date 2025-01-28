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
import java.util.ArrayList;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class backend
 */
@WebServlet("/backend")
public class backend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		Connection conn=null;
		  try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              System.out.println("sucessfull");
              conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todo", "root", "");
            
          } catch (Exception e) {
              e.printStackTrace();
          }
		  String name="";
		  String email="";
		  byte[] img = null;
		  try {
			PreparedStatement preparedStatement= conn.prepareStatement("select * from basicinfo");
			ResultSet r= preparedStatement.executeQuery();
			String prefix="";
			while(r.next())
			{
			     img=r.getBytes("image");
			     name=r.getString("name");
			     email=r.getString("email");
			     prefix=r.getString("prefix");
			}
			System.out.println(prefix);
			String convertedimage=Base64.getEncoder().encodeToString(img);
			JsonObject resobj=new JsonObject();
			resobj.addProperty("email", email);
			resobj.addProperty("name", name);
			resobj.addProperty("image", convertedimage);
			resobj.addProperty("prefix", prefix);
			res.setContentType("application/json");
			res.getWriter().write(resobj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
