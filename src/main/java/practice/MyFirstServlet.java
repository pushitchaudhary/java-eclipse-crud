package practice;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter printOut = response.getWriter();
		response.setContentType("text/html");
//		printOut.print("hello world");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter printOut = response.getWriter();

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String age = request.getParameter("age");
	    String address = request.getParameter("address");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        String url = "jdbc:mysql://localhost:3306/college_app";
	        String user = "root";
	        String pass = "";
	        Connection con = DriverManager.getConnection(url, user, pass);

	        String query = "INSERT INTO user (name, email, age, address) VALUES (?, ?, ?, ?)";
	        PreparedStatement st = con.prepareStatement(query);

	        st.setString(1, name);
	        st.setString(2, email);
	        st.setString(3, age);
	        st.setString(4, address);

	        int result = st.executeUpdate();

	        if (result > 0) {
	            printOut.println("<h1> Your account is created </h1>");
	            printOut.println("<p> Name " + name + "</p>");
	            printOut.println("<p> Email " + email + "</p>");
	            printOut.println("<p> Age " + age + "</p>");
	            printOut.println("<p> Address " + address + "</p>");
	        } else {
	            printOut.println("<h1> Sorry, your data is not registered </h1>");
	        }
	    } catch (Exception e) {
	        printOut.println("Please enter correct data !!");
	        e.printStackTrace(printOut); // Print stack trace to see any errors
	    }
	}
}

