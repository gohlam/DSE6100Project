import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private VideoDAO videoDAO;
    private FavVideoDAO favVideoDAO;
    private ReviewDAO reviewDAO;
    private User user;
    private QuestionDAO questionDAO;
 
    public void init() {
        userDAO = new UserDAO();
        initializeDAO = new InitializeDAO();
        videoDAO = new VideoDAO();
        favVideoDAO = new FavVideoDAO();
        reviewDAO = new ReviewDAO();
        questionDAO = new QuestionDAO();
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
            	break;
            case "/all":
            	showAllVideos(request, response);
            	break;
            case "/favorites":
            	showAllFavorites(request, response);
            	break;
            case "/search":
            	getSearchResults(request, response);
            	break;
            case "/remove":
            	removeVideoFromFav(request, response);
            	break;
            case "/video":
            	showVideoDetails(request, response);
            	break;
            case "/add":
            	addFavVideo(request, response);
            	break;
            case "/addReview":
            	addReview(request, response);
            	break;
            case "/updateReview":
            	updateReview(request, response);
            	break;
			case "/removeReview":
            	removeReview(request, response);
            	break;
			case "/upload":
				showVideoForm(request, response);
				break;
			case "/insertVideo":
				insertVideo(request, response);
			case "/addQuestion":
				addQuestion(request, response);	
				break;
		    	case "/removeQuestion":
				removeQuestion(request, response);
			    	break;
            default:
            	showLoginForm(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
// insert or remove questions
	
     private void addQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
  	  		String questionID = request.getParameter("QuestionID");
	    	String question = request.getParameter("question");
	    	String tag = request.getParameter("tag");
	    	Question question = new Review(question, tag, (String) session.getAttribute("email"), questionID);
	    	QuestionDAO.addQuestion(question);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }

    private void removeQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	        String questionID = request.getParameter("questionID");
	        QuestionDAO.removeQuestion(questionID, (String) session.getAttribute("email"));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
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
	              HttpSession session = request.getSession();
	        	  session.setAttribute("email", tempUser.email);
	        	  session.setAttribute("name", tempUser.firstName + " " + tempUser.lastName);
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
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	System.out.println(email);
  	  	if (email != null) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");
	        dispatcher.forward(request, response);
  	  	} else {
	  	  	RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
	        dispatcher.forward(request, response);
  	  	}
    }
    
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String birthString = request.getParameter("birthday");
        Date birthday = null;
		try {
			birthday = format.parse(birthString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String gender = request.getParameter("gender");
        User newUser = new User(email, firstName, lastName, password, birthday, gender);
        User tempUser = userDAO.getUser(email);
        if(tempUser == null) {
	        if (password.equals(password2)) {
		        userDAO.insert(newUser);
		        user = newUser;
		        request.setAttribute("user", newUser);       
		        RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	            dispatcher.forward(request, response);
	        } else {
	        	 request.setAttribute("url", "new");
	        	 request.setAttribute("message", "Passwords do not match. Please try again");
	        	 RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");       
	             dispatcher.forward(request, response);
	        }
        } else {
       	 request.setAttribute("url", "new");
       	 request.setAttribute("message", "Email already exists.");
       	 RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");       
            dispatcher.forward(request, response);
       }
    }
    
    private void initializeDB(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	    	initializeDAO.connect_func();
	    	initializeDAO.dropTables();
	    	userDAO.initializeUser();
	    	initializeDAO.initializeQuestion();
	    	initializeDAO.initializeTag();
	    	initializeDAO.initializeVideo();
	    	initializeDAO.initializeFavVideo();
	    	initializeDAO.initializeReview();
	    	initializeDAO.disconnect();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  		
  	  	}
    }   
    
    private void showAllVideos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	    	List<Video> videos = videoDAO.getAllVideos();
	    	List<String> favVideos = favVideoDAO.getUsersFavVideosURLs(email);
	    	List<String> reviewedVideos = reviewDAO.usersReviewedVids(email);
	      	request.setAttribute("videos", videos);
	      	request.setAttribute("favVideos", favVideos);
	      	request.setAttribute("reviewedVideos", reviewedVideos);
	      	RequestDispatcher dispatcher = request.getRequestDispatcher("Videos.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void showAllFavorites(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	    	List<Video> videos = favVideoDAO.getUsersFavVideos(email);
	    	List<String> favVideos = favVideoDAO.getUsersFavVideosURLs(email);
	    	List<String> reviewedVideos = reviewDAO.usersReviewedVids(email);
	      	request.setAttribute("favVideos", favVideos);
	    	request.setAttribute("videos", videos);
	      	request.setAttribute("reviewedVideos", reviewedVideos);
	      	RequestDispatcher dispatcher = request.getRequestDispatcher("Videos.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void removeVideoFromFav(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	        String url = request.getParameter("url");
	        favVideoDAO.removeFavVideo((String) session.getAttribute("email"), url);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	        dispatcher.forward(request, response);    
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void addFavVideo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	        String url = request.getParameter("url");
	        favVideoDAO.addFavVideo(email, url);
	        response.sendRedirect("favorites");  // The sendRedirect() method works at client side and sends a new request
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
  	  }
    
    private void getSearchResults(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
  	  		String searchVals = request.getParameter("keyword");
  	  		List<Video> searchVideos = videoDAO.getSearchResults(searchVals);
	  	  	request.setAttribute("videos", searchVideos);
	  	  	List<String> favVideos = favVideoDAO.getUsersFavVideosURLs(email);
	  	  	System.out.println(favVideos);
	    	List<String> reviewedVideos = reviewDAO.usersReviewedVids(email);
	      	request.setAttribute("favVideos", favVideos);
	      	request.setAttribute("reviewedVideos", reviewedVideos);
	      	RequestDispatcher dispatcher = request.getRequestDispatcher("Videos.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    
    }
    
    private void showVideoDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	  	  	String url = request.getParameter("url");
	        List<String> favVideo = favVideoDAO.getUsersFavVideosURLs((String) session.getAttribute("email"));
	        if(favVideo.contains(url)) {
	        	request.setAttribute("favorited", true);
	        }
	        Video video = videoDAO.getVideo(url);
	        request.setAttribute("video", video);
	        Review reviewedVideo = reviewDAO.getReview(url, (String) session.getAttribute("email"));
	        request.setAttribute("review", reviewedVideo);
	        if(reviewedVideo.url.contentEquals(url)) {
	            request.setAttribute("reviewed", true);
	        } else {
	            request.setAttribute("reviewed", false);
	        }
	      	RequestDispatcher dispatcher = request.getRequestDispatcher("Video.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  		
  	  	}
    }
    
    private void addReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
  	  		String url = request.getParameter("url");
	    	String comment = request.getParameter("comment");
	    	String score = request.getParameter("score");
	    	Review review = new Review(comment, score, (String) session.getAttribute("email"), url);
	    	reviewDAO.addReview(review);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void updateReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	        String url = request.getParameter("url");
	    	String comment = request.getParameter("comment");
	    	String score = request.getParameter("score");
	    	Review review = new Review(comment, score, (String) session.getAttribute("email"), url);
	    	reviewDAO.updateReview(review);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void removeReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	        String url = request.getParameter("url");
	        reviewDAO.removeReview(url, (String) session.getAttribute("email"));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.jsp");       
	        dispatcher.forward(request, response);
  	  	} else {
  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
         dispatcher.forward(request, response);
  	  	}
    }
    
    private void showVideoForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
  	  		List<Question> questionList = questionDAO.getQuestions();
  	  		request.setAttribute("questions", questionList);
  	  		RequestDispatcher dispatcher = request.getRequestDispatcher("InsertVideo.jsp");
	        dispatcher.forward(request, response);
  	  	} else {
  	  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
  	         dispatcher.forward(request, response);
  	  	}
    	
    }
    
    private void insertVideo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
  	  	String email = (String) session.getAttribute("email");
  	  	if (email != null) {
	  	  	String url = request.getParameter("url");
	    	String title = request.getParameter("title");
	    	String description = request.getParameter("description");
	    	String qid = request.getParameter("qid");
	    	Video vid = new Video(url, title, description, Integer.valueOf(qid), (String) session.getAttribute("email"));
	    	videoDAO.insertVideo(vid);
  	  	} else {
  	  	  	 RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
  	         dispatcher.forward(request, response); 	
  	  	}
    	
    }
    
    
}
