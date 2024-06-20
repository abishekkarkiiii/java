package backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/Backend")
public class Backend extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	  res.setContentType("application/json");
             JsonObject obj= JsonParser.parseReader(req.getReader()).getAsJsonObject();
            String path="E:\\image.jpg";
            String[] splits=obj.get("img").getAsString().split(",");
            ArrayList<String> arr= new ArrayList<String>();
          
            String base64="";
            if(splits.length==2)
            {
            	arr.add(splits[0]);
            	base64=splits[1];
            }
            byte [] bytes= Base64.getDecoder().decode(base64);
         
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
            
          
            String encodedBase64 = Base64.getEncoder().encodeToString(bytes);
            System.out.println(splits[0]);
            arr.add(encodedBase64);
            
             OutputStream o = new FileOutputStream(path);
             o.write(bytes);
             System.out.println(bytes.length);
             JsonObject resJsonObject =new JsonObject();
             String objforres =new Gson().toJson(arr);
             resJsonObject.addProperty("image", objforres);
             res.getWriter().write(resJsonObject.toString());
             
    }
}
