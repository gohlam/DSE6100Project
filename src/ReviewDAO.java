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
import java.util.List;

@WebServlet("/ReviewDAO")
public class ReviewDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ReviewDAO() {
		
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
    
    
    public List<String> usersReviewedVids(String email) throws SQLException {
    	connect_func();         
		String sql = "Select URL FROM Review where email = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		
        resultSet = preparedStatement.executeQuery();
        List<String> reviewedVids = new ArrayList<String>();
        while (resultSet.next()) {
            reviewedVids.add(resultSet.getString("URL"));
        }
        preparedStatement.close();
        statement.close();
        disconnect();
        return reviewedVids;
    }
    
    public void removeReview(String url, String email) throws SQLException {
    	connect_func();         
		String sql = "DELETE FROM Review WHERE email = ? AND URL = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, url);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public void addReview(Review review) throws SQLException {
    	connect_func();         
		String sql = "insert into Review(comment, score, email, URL, reviewDate) values (?, ?, ?, ?, CURDATE())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, review.comment);
		preparedStatement.setString(2, review.score);
		preparedStatement.setString(3, review.email);
		preparedStatement.setString(4, review.url);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();  
    }
    
    public Review getReview(String url, String email) throws SQLException {
    	connect_func();         
		String sql = "Select * FROM Review where email = ? and url = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, url);

        resultSet = preparedStatement.executeQuery();
        Review temp = new Review();
        temp.setURL("");
        while (resultSet.next()) {
            temp.setURL(resultSet.getString("URL"));
            temp.setComment(resultSet.getString("comment"));
            temp.setDate(resultSet.getString("reviewDate"));
            temp.setEmail(resultSet.getString("email"));
            temp.setScore(resultSet.getString("score"));
        }
        preparedStatement.close();
        statement.close();
        resultSet.close();
        disconnect();
        return temp;
    	
    }
    
    public void updateReview(Review review) throws SQLException {
    	connect_func();         
		String sql = "Update Review SET comment = ?, score = ?, reviewDate = CURDATE() where email = ? and URL = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, review.comment);
		preparedStatement.setString(2, review.score);
		preparedStatement.setString(3, review.email);
		preparedStatement.setString(4, review.url);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();  
    }

}
