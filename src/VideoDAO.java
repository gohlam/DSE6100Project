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
    	String sql = "Select * from Video;";
    	resultSet = statement.executeQuery(sql);
    	String url;
    	String title;
    	String description;
    	int qid;
    	String email;
    	String date;
    	List<Video> videos = new ArrayList<Video>();
    	Video temp;
    	
        while (resultSet.next()) {
            url = resultSet.getString("URL");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getInt("qid");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            temp = new Video(url, title, description, qid, email, date);
            videos.add(temp);
        }

        resultSet.close();
        statement.close();
        disconnect();
        return videos;
   }

	public Video getVideo(String url) throws SQLException {
		connect_func();
    	String sql = "Select * from Video where URL = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, url);
        resultSet = preparedStatement.executeQuery();
    	String title;
    	String description;
    	int qid;
    	String email;
    	String date;
    	Video video = null;
    	
        while (resultSet.next()) {
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            qid = resultSet.getInt("qid");
            email = resultSet.getString("email");
            date = resultSet.getString("postdate");  
            video = new Video(url, title, description, qid, email, date);
        }

        preparedStatement.close();
        resultSet.close();
        statement.close();
        disconnect();
        return video;
	}

	public List<Video> getSearchResults(String searchVals) throws SQLException {
		List<Video> videos = new ArrayList<>();
		searchVals = removePunctuation(searchVals);
		String[] splitVals = searchVals.split(" ");
		String query = buildQuery(splitVals);
		System.out.println(query);
		if (query != null) {
			connect_func();
	    	resultSet = statement.executeQuery(query);
	    	Video temp;
	    	String url;
	    	String title;
	    	String description;
	    	int qid;
	    	String email;
	    	String date;
	    	
	        while (resultSet.next()) {
	            url = resultSet.getString("URL");
	            title = resultSet.getString("title");
	            description = resultSet.getString("description");
	            qid = resultSet.getInt("qid");
	            email = resultSet.getString("email");
	            date = resultSet.getString("postdate");  
	            temp = new Video(url, title, description, qid, email, date);
	            videos.add(temp);
	        }
	        
	        resultSet.close();
	        statement.close();
	        disconnect();
		}
        return videos;
	}
	
	private String buildQuery(String[] keywords) {
		String query = "";
		if(keywords.length > 0) {
			for(String s: keywords) {
				query = query + "SELECT * FROM Video WHERE title LIKE '%" + s + "%' UNION ";
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
