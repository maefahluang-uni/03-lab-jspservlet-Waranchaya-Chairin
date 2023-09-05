package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet ("/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String weightPar = request.getParameter("weight");
        String heightPar = request.getParameter("height");

        if (weightPar != null && !weightPar.isEmpty() && heightPar != null && !heightPar.isEmpty()) {
        
        try {

        double weight = Double.parseDouble(weightPar);
        double height = Double.parseDouble(heightPar);

        
        //TODO: calculate bmi
        double bmi = weight / Math.pow(height,2) ;

        //TODO: determine the built from BMI
        String built = determineBuild(bmi);
      
        //TODO: add bmi and built to the request's attribute
        request.setAttribute("bmi", Math.round(bmi));
        request.setAttribute("built", built);

        //TODO: forward to jsp
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid weight or height values");
        }
           
    }
    
}

private String determineBuild(Double bmi) {
    if (bmi < 18.5){
        return "underweight";
    } else if (bmi < 25) {
        return "normal";
    } else if (bmi < 30) {
        return "overweight";
    } else if (bmi < 35) {
        return "obese";
    } else {
        return "extremely obese";
    }
}
}
