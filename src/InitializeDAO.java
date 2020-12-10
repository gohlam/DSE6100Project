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
    	statement.executeUpdate("DROP TABLE IF EXISTS Video");
    	statement.executeUpdate("DROP TABLE IF EXISTS Question");
    	statement.executeUpdate("DROP TABLE IF EXISTS User");
    }
    
    
    public void initializeVideo() throws SQLException {
    	String sqlCreateVideo = "CREATE TABLE IF NOT EXISTS Video ( URL VARCHAR(250) NOT NULL, " + 
    			"title VARCHAR(100), description VARCHAR(350), qid INTEGER, email VARCHAR(100), " + 
    			"postdate DATE, primary key(URL), foreign key(email) references User(email), foreign key (qid) references Question(questionID))";
    	statement.executeUpdate(sqlCreateVideo);
    	String sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/nRlZAjtEQ_8', 'How to Tile A Shower', "
    			+ "'This is a comprehensive Tutorial for anyone looking to learn how to install really large tile! We will show You how to install, level, grout and trim plus discuss all the best tools and techniques to getting the job done.', 1, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/hM0LLNZqv1s', 'How to Replace Damaged Vinyl Siding', "
    			+ "'Today Im sharing with my secret vinyl siding weapon. Its a vinyl siding tool that costs less than ten bucks, and can save you tens of thousands of dollars in the long run! If youre working with vinyl siding you need this tool in your back pocket.', 2, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/dyDJjPWbRHU', 'How to Install Trim', "
    			+ "'Todays video is a special one; its an A to Z all things finished carpentry! Were going to be covering so many things from filler strips, to toe kicks, how to trim out old windows and make custom sills, baseboards, quarter round, shoe molds, door casings.', 3, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/baCMjhENGEE', 'How to Install Laminate Countertops', 'Forget about granite or quartz!!! In this video learn why I installed laminate and how to install it in your new kitchen to get a million dollar look!', 4, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/PkvKO8GchyE', 'How to Build a Deck', 'How to use Lowes Deck Designer Software to design a deck and build it.', 5, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/xi1b5vvTDWk', 'DIY Staircase Makeover', 'See all the steps we took in this DIY Staircase Makeover to transforming our old staircase into an entryway statement piece.', 6, 'allison@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/2G5f_LpdLh0', '30 Easy DIY Projects For Beginners ', 'We have a bunch of cool DIY projects lined up just for you so take your pick and have fun creating something special.', 7, 'allison@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/knBsMeDM5Ac', 'DIY Small Bathroom Remodel', 'This bath renovation included how to install a shower surround with tile, installing a toilet, new vanity and lighting. ', 8, 'allison@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/CRXCB_3gLok', 'How to Paint a Room', 'Here are tips for painting a room, including how to get started, techniques, using rollers, finishing and how to clean up.', 9, 'allison@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/7ZCeqcrftDk', '10 EASY + INEXPENSIVE HOME IMPROVEMENTS', '10 easy + inexpensive home projects + DIYs that YOU can do yourself!', 10, 'junwen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/K3C3NWTGliE', 'Affordable DIY Kitchen Renovation (Before & After)', 'In this video, you'll see DIY concrete countertops, DIY chalk wall, DIY island & cabinets, DIY range & DIY brick backsplash all for $3,200! ', 10, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/ZeewhIe2fns', 'Remodeling a bathroom for Under $500 | DIY | How To', 'Today on Modern Builds I'm renovating / updating my old, ugly, small bathroom; all for under $500! ', 8, 'ameen@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES ('https://youtu.be/B16l7RurJuQ', 'DIY Fix Outlet', 'How to safely fix an outlet on your own', 11, '@wayne.edu', CURDATE())";
    	statement.executeUpdate(sql1);
    }
    
    public void initializeFavVideo() throws SQLException {
    	String sqlFavVideo = "CREATE TABLE IF NOT EXISTS FavoriteVideos (" + 
    			"email VARCHAR(100), URL VARCHAR(250), " + 
    			"primary key( email, URL), " + 
    			"foreign key(email) references User(email), " + 
    			"foreign key(URL) references Video(URL)) ";
    	statement.executeUpdate(sqlFavVideo);
    	String sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/nRlZAjtEQ_8')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/hM0LLNZqv1s')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/dyDJjPWbRHU')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/baCMjhENGEE')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('ameen@wayne.edu', 'https://youtu.be/PkvKO8GchyE')";
    	statement.executeUpdate(sql1);
	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('allison@wayne.edu', 'https://youtu.be/xi1b5vvTDWk')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('allison@wayne.edu', 'https://youtu.be/2G5f_LpdLh0')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('allison@wayne.edu', 'https://youtu.be/knBsMeDM5Ac')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('allison@wayne.edu', 'https://youtu.be/CRXCB_3gLok')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO FavoriteVideos(email, URL) VALUES ('allison@wayne.edu', 'https://youtu.be/7ZCeqcrftDk')";
    	statement.executeUpdate(sql1);	
    }
    
    public void initializeReview() throws SQLException {
    	String sqlCreateReview = "CREATE TABLE IF NOT EXISTS Review ( " + 
    			"comment VARCHAR(250), score VARCHAR(20), email VARCHAR(100), " + 
    			"URL VARCHAR(250), reviewDate DATE, primary key (email, URL), " + 
    			"foreign key (email) references User(email), " + 
    			"foreign key (URL) references Video(URL))";
    	statement.executeUpdate(sqlCreateReview);
    	statement.executeUpdate(sqlCreateReview);
    	String sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('What a long video!! But it was so helpful', 'fair', 'ameen@wayne.edu', 'https://youtu.be/nRlZAjtEQ_8', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('Thank you Jeff! That was very brief and to the point', 'good', 'allison@wayne.edu', 'https://youtu.be/hM0LLNZqv1s', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('I absolutely love your videos :)', 'excellent', 'junwen@wayne.edu', 'https://youtu.be/dyDJjPWbRHU', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('Going to do this for my bathroom', 'good', 'sydney@wayne.edu', 'https://youtu.be/baCMjhENGEE', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('I knew I had to hire someone to build my deck as soon as he said parallelogram', 'fair', 'jared@wayne.edu', 'https://youtu.be/PkvKO8GchyE', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('Im watching this but I dont even have stairs in my home... thank you anyway!', 'good', 'ginny@wayne.edu', 'https://youtu.be/xi1b5vvTDWk', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('THIS MUSIC IN THE VIDEO DRIVES ME CRAZY!', 'fair', 'ameen@wayne.edu', 'https://youtu.be/2G5f_LpdLh0', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('Awesome video tutorial! Well done!', 'excellent', 'ameen@wayne.edu', 'https://youtu.be/knBsMeDM5Ac', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('Did they just tell me to put toxic paint and a dirty brush into my fridge??', 'poor', 'allison@wayne.edu', 'https://youtu.be/CRXCB_3gLok', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('The kitchen painting project was a big one, but it majorly paid off', 'good', 'junwen@wayne.edu', 'https://youtu.be/7ZCeqcrftDk', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('This video did not help me at all', 'poor', 'allison@wayne.edu', 'https://youtu.be/B16l7RurJuQ', CURDATE())";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Review(comment, score, email, URL, reviewDate) VALUES ('These ideas were all excellent!', 'good', 'ginny@wayne.edu', 'https://youtu.be/7ZCeqcrftDk', CURDATE())";
    	statement.executeUpdate(sql1);
    }
    
    public void initializeQuestion() throws SQLException {
    	String sqlCreateQuestion = "CREATE TABLE IF NOT EXISTS Question ( " + 
    			"questionID integer NOT NULL AUTO_INCREMENT, question VARCHAR(250)," +
    			" date DATE, email VARCHAR(100), " + 
    			"foreign key (email) references User(email), " + 
    			"primary key (questionID)) ";
    	statement.executeUpdate(sqlCreateQuestion);
    	String sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to paint a wall?', CURDATE(), 'ameen@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to repair vinyl siding?', CURDATE(), 'allison@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to install trim?', CURDATE(), 'jared@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to install countertops?', CURDATE(), 'ameen@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('DIY deck backyard?', CURDATE(), 'junwen@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('Staircase makeover ideas', CURDATE(), 'ginny@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('DIY easy home updates', CURDATE(), 'ameen@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to update an old bathroom?', CURDATE(), 'sydney@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to install tile?', CURDATE(), 'junwen@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('DIY home improvements', CURDATE(), 'allison@wayne.edu')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Question(question, date, email) VALUES ('How to fix an outlet?', CURDATE(), 'allison@wayne.edu')";
    	statement.executeUpdate(sql1);
    }
    
    public void initializeTag() throws SQLException {
    	String sqlCreateTag = "CREATE TABLE IF NOT EXISTS Tag ( questionID integer, " + 
    			"tag VARCHAR(50), primary key (questionID, tag), " + 
    			"foreign key (questionID) references Question(questionID))";
    	statement.executeUpdate(sqlCreateTag);
    	String sql1 = "INSERT INTO Tag(questionID, tag) VALUES (1, 'paint')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (1, 'brush')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (1, 'room')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (1, 'easy')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (7, 'easy')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (7, 'makeover')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (7, 'easy')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (7, 'update')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (3, 'update')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (9, 'update')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (6, 'makeover')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (6, 'staircase')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (8, 'update')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (8, 'makeover')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (8, 'easy')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (10, 'update')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (10, 'makeover')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (10, 'easy')";
    	statement.executeUpdate(sql1);
    	sql1 = "INSERT INTO Tag(questionID, tag) VALUES (11, 'easy')";
    	statement.executeUpdate(sql1);
    }
    
}
