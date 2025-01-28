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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Display
 */
@WebServlet("/display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	
		Connection conn=null;
		  try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("sucessfull");
          conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todo", "root", "");
        
      } catch (Exception e) {
          e.printStackTrace();
      }
		  
		  try {
			  ArrayList<String> arrdata=new ArrayList<>();
			  ArrayList<String> arrid=new ArrayList<>();
				PreparedStatement stmt=conn.prepareStatement("select * from information");
	
					ResultSet r=stmt.executeQuery();
					while(r.next())
					{
						arrdata.add(r.getString("data"));
						arrid.add(r.getString("dataid"));
						
					}
				
				Gson converter = new Gson();
				String arraydatatosend=converter.toJson(arrdata);
				String arraydataidtosend=converter.toJson(arrid);
				JsonObject objtosend= new JsonObject();
				objtosend.addProperty("data", arraydatatosend);
				objtosend.addProperty("id", arraydataidtosend);
				res.setContentType("application/json");
				res.getWriter().write(objtosend.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
