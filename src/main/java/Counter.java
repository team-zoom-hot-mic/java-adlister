import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Counter", urlPatterns = "/count")

public class Counter extends HttpServlet {
    int count = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count ++;
//        count -= 1;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h4>This page has been viewed: " + count + " times.</h4>");
    }
}