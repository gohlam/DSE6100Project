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
/**
 * Servlet implementation class Connect
 */
@WebServlet("/UserDAO")
public class UserDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public UserDAO() {

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
    
    public void initializeUser() throws SQLException {
    	String sqlCreateUser = "CREATE TABLE IF NOT EXISTS User ( \n" + 
    			"email VARCHAR(100), password VARCHAR(50) NOT NULL, " + 
    			"firstName VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " + 
    			"birthday VARCHAR(30), gender VARCHAR(15), " + 
    			"primary key(email))";
    	statement.executeUpdate(sqlCreateUser);
    	//TODO insert records
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(User user) throws SQLException {
    	connect_func();         
		String sql = "insert into  User(email, password, firstName, lastName, birthday, gender) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.email);
		preparedStatement.setString(2, user.password);
		preparedStatement.setString(3, user.firstName);
		preparedStatement.setString(4, user.lastName);
		preparedStatement.setString(5, user.birthday);
		preparedStatement.setString(6, user.gender);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public User getUser(String email) throws SQLException {
    	connect_func();
    	String sql = "Select * from User where email=?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		resultSet = preparedStatement.executeQuery();
        String firstName = "";
        String lastName = "";
        String password = "";
        String gender = "";
        String birthday = "";
        User user = null;
     
        if (resultSet.next()) {
            firstName = resultSet.getString("firstName");
            lastName = resultSet.getString("lastName");
            password = resultSet.getString("password");
            gender = resultSet.getString("gender");
            birthday = resultSet.getString("birthday");  
            user = new User(email, firstName, lastName, password, birthday, gender);
        }

        preparedStatement.close(); 
        resultSet.close();
        statement.close();
        disconnect();
        return user;
    }

}
