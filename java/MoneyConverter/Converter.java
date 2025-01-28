package MoneyConverter;

import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/backend")
public class Converter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json");

        
        JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();

        double amount = json.get("amount").getAsDouble();
        String fromCurrency = json.get("fromCurrency").getAsString();
        String toCurrency = json.get("toCurrency").getAsString();

        double conversionRate = getConversionRate(fromCurrency, toCurrency);

        // Convert the amount
        double convertedAmount = amount * conversionRate;

       
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", "Conversion successful");
        jsonResponse.addProperty("convertedAmount", convertedAmount);

 
        response.getWriter().write(jsonResponse.toString());
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
      
        if (fromCurrency.equals("USD") && toCurrency.equals("NPR")) {
            return 132.09; // Example conversion rate
        } else if (fromCurrency.equals("INR") && toCurrency.equals("NPR")) {
            return 1.58; // Example conversion rate
        } else if(fromCurrency.equals("NPR") && toCurrency.equals("INR")){
        	return 0.63;
        }else if(fromCurrency.equals("NPR") && toCurrency.equals("USD")){
        	return 0.0076;
        }else if(fromCurrency.equals("USD") && toCurrency.equals("INR")){
        	return 83.53;
        }else if(fromCurrency.equals("INR") && toCurrency.equals("USD")){
        	return 0.012;
        }else {
            return 1.0; 
        }
    }
}
