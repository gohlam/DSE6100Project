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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/VideoDAO")
public class VideoDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
		
		
	public VideoDAO() {

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
	    
    public void initializeVideo() throws SQLException {
    	String sqlCreateVideo = "CREATE TABLE IF NOT EXISTS Video ( URL VARCHAR(250) NOT NULL, " + 
    			"title VARCHAR(100), description VARCHAR(350), qid INTEGER, email VARCHAR(100)" + 
    			"postdate DATE, primary key(URL), foreign key(email) references User(email), foreign key (qid) references Question(questionID))";
    	statement.executeUpdate(sqlCreateVideo);
    }
	    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public List<Video> getAllVideos () throws SQLException {
    	connect_func();
    	String sql = "Select * from Video as V, Question as Q WHERE V.qid = Q.questionID;";
    	resultSet = statement.executeQuery(sql);
    	String url;
    	String title;
    	String description;
    	String qid;
    	String question;
    	String email;
    	String date;
    	List<Video> videos = new ArrayList<Video>();
    	Video temp;
    	
        while (resultSet.next()) {
            url = resultSet.getString("URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, question, email, date);
            videos.add(temp);
        }

        resultSet.close();
        statement.close();
        disconnect();
        return videos;
   }

	public Video getVideo(String url) throws SQLException {
		connect_func();
    	String sql = "Select * from Video as V, Question as Q where URL = ? AND V.qid = Q.questionID";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, url);
        resultSet = preparedStatement.executeQuery();
    	String title;
    	String description;
    	String qid;
    	String question;
    	String email;
    	String date;
    	Video video = null;
    	
        while (resultSet.next()) {
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            video = new Video(url, title, description, qid, question, email, date);
        }

        preparedStatement.close();
        resultSet.close();
        statement.close();
        disconnect();
        return video;
	}
	
	public void insertVideo(Video video) throws SQLException {
		connect_func();
    	String sql = "INSERT INTO Video(URL, title, description, qid, email, postdate) VALUES (?, ?, ?, ?, ?, CURDATE())";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, video.url);
		preparedStatement.setString(2, video.title);
		preparedStatement.setString(3, video.description);
		preparedStatement.setString(4, video.qid);
		preparedStatement.setString(5, video.email);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();
	}

	public List<Video> getSearchResults(String searchVals) throws SQLException {
		List<Video> videos = new ArrayList<>();
		searchVals = removePunctuation(searchVals);
		String[] splitVals = searchVals.split(" ");
		String query = buildQuery(splitVals);
		if (query != null) {
			connect_func();
	    	resultSet = statement.executeQuery(query);
	    	Video temp;
	    	String url;
	    	String title;
	    	String description;
	    	String qid;
	    	String question;
	    	String email;
	    	String date;
	    	
	        while (resultSet.next()) {
	            url = resultSet.getString("URL");
	            title = resultSet.getString("title");
	            description = resultSet.getString("description");
	            qid = resultSet.getString("qid");
	            question = resultSet.getString("question");
	            email = resultSet.getString("email");
	            date = resultSet.getString("postdate");  
	            temp = new Video(url, title, description, qid, question, email, date);
	            videos.add(temp);
	        }
	        
	        resultSet.close();
	        statement.close();
	        disconnect();
		}
        return videos;
	}
	
	public List<Video> getCoolVideos() throws SQLException {
		List<Video> videos = new ArrayList<>();
		connect_func();
		String sql = "Select * from Video as V, Question as Q " + 
				"WHERE V.qid = Q.questionID AND V.URL IN " + 
				"(SELECT DISTINCT V3.URL from Video as V3, Review as R2 WHERE V3.URL = R2.URL " + 
				"AND V3.URL NOT IN( " + 
				"SELECT DISTINCT V2.URL from Video as V2, Review as R " + 
				"WHERE (R.score = 'poor' OR R.score = 'fair' OR R.score = 'good') AND V2.URL = R.URL))";
		resultSet = statement.executeQuery(sql);
    	Video temp;
    	String url;
    	String title;
    	String description;
    	String qid;
    	String question;
    	String email;
    	String date;
    	
		while (resultSet.next()) {
			url = resultSet.getString("URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, question, email, date);
            videos.add(temp);
		}
		
	    resultSet.close();
	    statement.close();
	    disconnect();
		return videos;
	}
	
	public List<Video> getVideosByQid(String questionID) throws SQLException {
		List<Video> videos = new ArrayList<>();
		connect_func();
		String sql = "Select * from Video as V, Question Q WHERE V.qid = Q.questionID AND Q.questionID = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, questionID);
        resultSet = preparedStatement.executeQuery();
        Video temp;
    	String url;
    	String title;
    	String description;
    	String qid;
    	String question;
    	String email;
    	String date;
        while (resultSet.next()) {
        	url = resultSet.getString("URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, question, email, date);
            videos.add(temp);
        }
		resultSet.close();
	    preparedStatement.close();
	    disconnect();
		return videos;
	}
	
	public List<Video> getTopReviewedVideos() throws SQLException {
		List<Video> videos = new ArrayList<>();
		connect_func();
		String sql = "SELECT V.URL, title, description, qid, V.email, postdate, Q.question " +
				"from Video as V, Question as Q, Review as R WHERE Q.questionID = V.qid AND V.URL = R.URL GROUP BY V.URL ORDER BY COUNT(*) DESC " +
				"LIMIT 3";
		resultSet = statement.executeQuery(sql);
		Video temp;
    	String url;
    	String title;
    	String description;
    	String qid;
    	String question;
    	String email;
    	String date;
		while (resultSet.next()) {
			url = resultSet.getString("V.URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            email = resultSet.getString("V.email");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, question, email, date);
            videos.add(temp);
		}
		resultSet.close();
	    statement.close();
	    disconnect();
		return videos;
	}
	
	public List<Video> getVideosByUser(String email) throws SQLException {
		List<Video> videos = new ArrayList<>();
		connect_func();
		String sql = "Select * From Video as V, Question as Q WHERE Q.questionID = V.qid AND V.email = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        Video temp;
    	String url;
    	String title;
    	String description;
    	String qid;
    	String question;
    	String date;
        while (resultSet.next() ) {
        	url = resultSet.getString("URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getString("qid");
            question = resultSet.getString("question");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, question, email, date);
            videos.add(temp);
        }
        resultSet.close();
	    preparedStatement.close();
	    disconnect();
		return videos;
	}
	
	private String buildQuery(String[] keywords) {
		String query = "";
		if(keywords.length > 0) {
			for(String s: keywords) {
				query = query + "SELECT * FROM Video as V, Question as Q  WHERE V.qid = Q.questionID AND title LIKE '%" + s + "%' UNION ";
			}
		}
		query = query.substring(0, query.length() - 7);
		return query;
	}
	
	private String removePunctuation(String search) {
		List<String> chars = new ArrayList<>(Arrays.asList(".", ",", "?", "/", "!"));
		for (String c: chars) {
			search = search.replace(c, "");
		}
		return search;
	}

}
