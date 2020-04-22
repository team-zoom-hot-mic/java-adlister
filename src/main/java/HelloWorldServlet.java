import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration<String> names = request.getAttributeNames();
        if(!names.hasMoreElements()) {
            System.out.println("Nothing here!");
        }
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = request.getAttribute(name);
            System.out.println("name: " + name + ", value: " + value);
        }
        response.getWriter().println("<h1>Hello, World!</h1>");
    }
}
