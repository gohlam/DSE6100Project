import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InitializeDAO")
public class InitializeDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public InitializeDAO() {
		
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?&serverTimezone=UTC&"
  			          + "user=john&password=pass1234");
            statement = connect.createStatement();
        }
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void dropTables() throws SQLException {
    	statement.executeUpdate("DROP TABLE IF EXISTS FavoriteVideos");
    	statement.executeUpdate("DROP TABLE IF EXISTS Post");
    	statement.executeUpdate("DROP TABLE IF EXISTS Tag");
    	statement.executeUpdate("DROP TABLE IF EXISTS Review");
    	statement.executeUpdate("DROP TABLE IF EXISTS User");
    	statement.executeUpdate("DROP TABLE IF EXISTS Video");
    	statement.executeUpdate("DROP TABLE IF EXISTS Question");
    }
    
    
    public void initializeVideo() throws SQLException {
    	String sqlCreateVideo = "CREATE TABLE IF NOT EXISTS Video ( URL VARCHAR(250), " + 
    			"title VARCHAR(100), description VARCHAR(350), " + 
    			"date DATE, primary key(URL))";
    	statement.executeUpdate(sqlCreateVideo);
    	// TODO insert records
    }
    
    public void initializeFavVideo() throws SQLException {
    	String sqlFavVideo = "CREATE TABLE IF NOT EXISTS FavoriteVideos (" + 
    			"email VARCHAR(100), URL VARCHAR(250), " + 
    			"primary key( email, URL), " + 
    			"foreign key(email) references User(email), " + 
    			"foreign key(URL) references Video(URL)) ";
    	statement.executeUpdate(sqlFavVideo);
    	//TODO insert records
    }
    
    public void initializeReview() throws SQLException {
    	String sqlCreateReview = "CREATE TABLE IF NOT EXISTS Review ( " + 
    			"comment VARCHAR(250), score VARCHAR(20), email VARCHAR(100), " + 
    			"URL VARCHAR(250), primary key (email, URL), " + 
    			"foreign key (email) references User(email), " + 
    			"foreign key (URL) references Video(URL))";
    	statement.executeUpdate(sqlCreateReview);
    	//TODO insert records
    }
    
    public void initializeQuestion() throws SQLException {
    	String sqlCreateQuestion = "CREATE TABLE IF NOT EXISTS Question ( " + 
    			"questionID integer NOT NULL AUTO_INCREMENT, question VARCHAR(250), date DATE, " + 
    			"primary key (questionID)) ";
    	statement.executeUpdate(sqlCreateQuestion);
    }
    
    public void initializeTag() throws SQLException {
    	String sqlCreateTag = "CREATE TABLE IF NOT EXISTS Tag ( questionID integer, " + 
    			"tag VARCHAR(50), primary key (questionID, tag), " + 
    			"foreign key (questionID) references Question(questionID))";
    	statement.executeUpdate(sqlCreateTag);
    }
    
    public void initializePost() throws SQLException {
    	String sqlCreatePost = "CREATE TABLE IF NOT EXISTS Post ( email VARCHAR(100), " + 
    			"URL VARCHAR(250), questionID integer, " + 
    			"primary key(email, questionID), " + 
    			"foreign key(email) references User(email), " + 
    			"foreign key(URL) references Video(URL), " + 
    			"foreign key(questionID) references Question(questionID))";
    	statement.executeUpdate(sqlCreatePost);
    }
    
}
