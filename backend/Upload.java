package backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/upload")
public class Upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todo", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject obj = JsonParser.parseReader(req.getReader()).getAsJsonObject();
        String name = obj.get("name").getAsString();
        String email = obj.get("email").getAsString();
        String imgData = obj.get("img").getAsString();
        String[] splitData = imgData.split(",");
        String pureImageData = "";
        String prefix = "";
        if (splitData.length == 2) {
            pureImageData = splitData[1];
            prefix = splitData[0];
        }


        byte[] imgBytes = Base64.getDecoder().decode(pureImageData);

        byte[] compressedImgBytes = compressImage(imgBytes);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE basicinfo SET name=?, email=?, image=?, prefix=? WHERE id=1");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setBytes(3, compressedImgBytes); // Store compressed image bytes
            preparedStatement.setString(4, prefix);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject resobj=new JsonObject();
        resobj.addProperty("name", name);
        resobj.addProperty("email", email);
        resobj.addProperty("image", imgData);
        res.setContentType("application/json");
        res.getWriter().write(resobj.toString());
    }


    private byte[] compressImage(byte[] imageBytes) {
        try {
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

      
            int newWidth = originalImage.getWidth() / 2;
            int newHeight = originalImage.getHeight() / 2;

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

        
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
