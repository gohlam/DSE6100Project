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

@WebServlet("/FavVideoDAO")
public class FavVideoDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public FavVideoDAO() {
		
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
    
    public void addFavVideo(String email, String url) throws SQLException {
    	connect_func();         
		String sql = "insert into FavoriteVideos(email, URL) values (?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, url);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public void removeFavVideo(String email, String url) throws SQLException {
    	connect_func();         
		String sql = "DELETE FROM FavoriteVideos WHERE email = ? AND URL = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, url);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public List<String> getUsersFavVideos(String email) throws SQLException {
    	connect_func();         
		String sql = "Select URL FROM FavoriteVideos where email = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		
        resultSet = preparedStatement.executeQuery();
        preparedStatement.close();
        statement.close();
        disconnect();
        List<String> favVideos = new ArrayList<String>();
        if (resultSet.next()) {
            favVideos.add(resultSet.getString("URL"));
        }
        return favVideos;
    	
    }

}
