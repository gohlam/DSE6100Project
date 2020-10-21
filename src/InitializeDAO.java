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
    	String date = java.time.LocalDate.now().toString();
    	System.out.println(date);
    	String sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/nRlZAjtEQ_8', 'How to Tile A Shower', "
    			+ "'This is a comprehensive Tutorial for anyone looking to learn how to install really large tile! We will show You how to install, level, grout and trim plus discuss all the best tools and techniques to getting the job done. ', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/hM0LLNZqv1s', 'How to Replace Damaged Vinyl Siding', "
    			+ "'Today Im sharing with my secret vinyl siding weapon. Its a vinyl siding tool that costs less than ten bucks, and can save you tens of thousands of dollars in the long run! If youre working with vinyl siding you need this tool in your back pocket.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/dyDJjPWbRHU', 'How to Install Trim', "
    			+ "'Todays video is a special one; its an A to Z all things finished carpentry! Were going to be covering so many things from filler strips, to toe kicks, how to trim out old windows and make custom sills, baseboards, quarter round, shoe molds, door casings.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/baCMjhENGEE', 'How to Install Laminate Countertops', 'Forget about granite or quartz!!! In this video learn why I installed laminate and how to install it in your new kitchen to get a million dollar look!', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/PkvKO8GchyE', 'How to Build a Deck', 'How to use Lowes Deck Designer Software to design a deck and build it.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/xi1b5vvTDWk', 'DIY Staircase Makeover', 'See all the steps we took in this DIY Staircase Makeover to transforming our old staircase into an entryway statement piece.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/2G5f_LpdLh0', '30 Easy DIY Projects For Beginners ', 'We have a bunch of cool DIY projects lined up just for you so take your pick and have fun creating something special.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/knBsMeDM5Ac', 'DIY Small Bathroom Remodel', 'This bath renovation included how to install a shower surround with tile, installing a toilet, new vanity and lighting. ', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/CRXCB_3gLok', 'How to Paint a Room', 'Here are tips for painting a room, including how to get started, techniques, using rollers, finishing and how to clean up.', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, date) VALUES ('https://youtu.be/7ZCeqcrftDk', '10 EASY + INEXPENSIVE HOME IMPROVEMENTS', '10 easy + inexpensive home projects + DIYs that YOU can do yourself!', CURDATE())";
    	statement.executeUpdate(sql1);
    }
    
    public void initializeFavVideo() throws SQLException {
    	String sqlFavVideo = "CREATE TABLE IF NOT EXISTS FavoriteVideos (" + 
    			"email VARCHAR(100), URL VARCHAR(250), " + 
    			"primary key( email, URL), " + 
    			"foreign key(email) references User(email), " + 
    			"foreign key(URL) references Video(URL)) ";
    	statement.executeUpdate(sqlFavVideo);
    	String sql1 = "INSERT INTO FavVideo(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/nRlZAjtEQ_8')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavVideo(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/hM0LLNZqv1s')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavVideo(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/dyDJjPWbRHU')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavVideo(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/baCMjhENGEE')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavVideo(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/PkvKO8GchyE')";
    	statement.executeUpdate(sql1);
    }
    
    public void initializeReview() throws SQLException {
    	String sqlCreateReview = "CREATE TABLE IF NOT EXISTS Review ( " + 
    			"comment VARCHAR(250), score VARCHAR(20), email VARCHAR(100), " + 
    			"URL VARCHAR(250), primary key (email, URL), " + 
    			"foreign key (email) references User(email), " + 
    			"foreign key (URL) references Video(URL))";
    	statement.executeUpdate(sqlCreateReview);
    	statement.executeUpdate(sqlCreateReview);
    	String sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('What a long video!! But it was so helpful', 'Fair', ameen@wayne.edu, 'https://youtu.be/nRlZAjtEQ_8')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('Thank you Jeff! That was very brief and to the point', 'Good', allison@wayne.edu, 'https://youtu.be/hM0LLNZqv1s')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('I absolutely love your videos :)', 'Excellent', junwen@wayne.edu, 'https://youtu.be/dyDJjPWbRHU')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('Going to do this for my bathroom', 'Good', sydney@wayne.edu, 'https://youtu.be/baCMjhENGEE')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('I knew I had to hire someone to build my deck as soon as he said parallelogram', 'Fair', jared@wayne.edu, 'https://youtu.be/PkvKO8GchyE')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('I'm watching this but I don't even have stairs in my home... thank you anyway!', 'Good', ginny@wayne.edu, 'https://youtu.be/xi1b5vvTDWk')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('THIS MUSIC IN THE VIDEO DRIVES ME CRAZY!', 'Fair', cassidy@wayne.edu, 'https://youtu.be/2G5f_LpdLh0')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('Awesome video tutorial! Well done!', 'Excellent', ameen@wayne.edu, 'https://youtu.be/knBsMeDM5Ac')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('Did they just tell me to put toxic paint and a dirty brush into my fridge??', 'Poor', allison@wayne.edu, 'https://youtu.be/CRXCB_3gLok')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL) VALUES ('The kitchen painting project was a big one, but it majorly paid off', 'Good', junwen@wayne.edu, 'https://youtu.be/7ZCeqcrftDk')";
    	statement.executeUpdate(sql1);
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
