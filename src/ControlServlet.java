import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private InitializeDAO initializeDAO;
    private User user;
 
    public void init() {
        userDAO = new UserDAO();
        initializeDAO = new InitializeDAO();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertUser(request, response);
                break;
            case "/initialize":
            	initializeDB(request, response);
            	break;
            case "/authenticate":
            	logUserIn(request, response);
            	break;
            case "/welcome":
            	showWelcomePage(request, response);
            default:
            	showLoginForm(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
 
    // to insert a user
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertUserForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
    }	
    
    private void logUserIn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	  String email = request.getParameter("email");
          String password = request.getParameter("password");
          User tempUser = userDAO.getUser(email);
          if(tempUser != null) {
	          if (password.equals(tempUser.password)) {
	        	  user = tempUser;
	        	  request.setAttribute("user", tempUser);
	        	  RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");
	              dispatcher.forward(request, response);
	          } else {
	         	  request.setAttribute("url", "");
	        	  request.setAttribute("message", "Incorrect credentials. Please try again");
	        	  RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");
	              dispatcher.forward(request, response);
	          }
          } else {
        	  request.setAttribute("url", "");
        	  request.setAttribute("message", "User does not exist. Please create a user with these credentials.");
        	  RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");
              dispatcher.forward(request, response);
          }
    	
    }
    
    private void showWelcomePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setAttribute("user", user);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");
        dispatcher.forward(request, response);
    }
    
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        User newUser = new User(email, firstName, lastName, password, birthday, gender);
        request.setAttribute("user", newUser);       
        if (password.equals(password2)) {
	        userDAO.insert(newUser);
	        user = newUser;
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
            dispatcher.forward(request, response);
        } else {
        	 request.setAttribute("url", "insert");
        	 request.setAttribute("message", "Passwords do not match. Please try again");
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");       
             dispatcher.forward(request, response);
        }
    }
    
    private void initializeDB(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	initializeDAO.connect_func();
    	userDAO.connect_func();
    	initializeDAO.dropTables();
    	userDAO.initializeUser();
    	initializeDAO.initializeVideo();
    	initializeDAO.initializeFavVideo();
    	initializeDAO.initializeReview();
    	initializeDAO.initializeQuestion();
    	initializeDAO.initializeTag();
    	initializeDAO.initializePost();
    	userDAO.disconnect();
    	initializeDAO.disconnect();
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");       
        dispatcher.forward(request, response);
    }   
}