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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    	connect_func();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	String sqlCreateUser = "CREATE TABLE IF NOT EXISTS User ( \n" + 
    			"email VARCHAR(100) NOT NULL, password VARCHAR(50) NOT NULL, " + 
    			"firstName VARCHAR(50), lastName VARCHAR(50), " + 
    			"birthday VARCHAR(30), gender VARCHAR(15), " + 
    			"primary key(email))";
    	statement.executeUpdate(sqlCreateUser);
    	String sql1 = "INSERT INTO User(email, password, firstName, lastName) VALUES ('root', 'pass1234', 'root', 'root')";
    	statement.executeUpdate(sql1);
        java.util.Date utilDate;
        java.sql.Date sqlDate = null; 
		try {
			utilDate = format.parse("08/13/1993");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('test1@wayne.edu', 'testpassword', 'Test', 'User', 'male', ?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
		sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('ameen@wayne.edu', 'testpassword', 'Ameen', 'A', 'male', ?)";
		try {
			utilDate = format.parse("5/23/1993");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('allison@wayne.edu', 'testpassword', 'Allison', 'G', 'female', ?)";
    	try {
			utilDate = format.parse("3/30/1995");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('test2@wayne.edu', 'testpassword2', 'Test2', 'User', 'female', ?)";
    	try {
			utilDate = format.parse("3/25/2001");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('junwen@wayne.edu', 'pass1234', 'Junwen', 'J', 'male', ?)";
    	try {
			utilDate = format.parse("6/7/1992");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('sydney@wayne.edu', 'pass5678', 'Sydney', 'T', 'female', ?)";
    	try {
			utilDate = format.parse("11/21/1995");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('jared@wayne.edu', 'password', 'Jared', 'G', 'male', ?)";
    	try {
			utilDate = format.parse("10/4/1997");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('ginny@wayne.edu', 'catsRcool', 'Ginny', 'F', 'female', ?)";
    	try {
			utilDate = format.parse("4/5/1998");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
    	sql1 = "INSERT INTO User(email, password, firstName, lastName, gender, birthday) VALUES ('cassidy@wayne.edu', 'pass5678', 'Cassidy', 'W', 'female', ?)";
    	try {
			utilDate = format.parse("7/15/1996");
	        sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.executeUpdate();
		preparedStatement.close();
        statement.close();
        disconnect();
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
		preparedStatement.setDate(5, new java.sql.Date(user.birthday.getTime()));
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
        Date birthday = null;
        User user = null;
     
        if (resultSet.next()) {
            firstName = resultSet.getString("firstName");
            lastName = resultSet.getString("lastName");
            password = resultSet.getString("password");
            gender = resultSet.getString("gender");
            birthday = resultSet.getDate("birthday");  
            user = new User(email, firstName, lastName, password, birthday, gender);
        }

        preparedStatement.close(); 
        resultSet.close();
        statement.close();
        disconnect();
        return user;
    }
    
    public List<String> getUsers() throws SQLException {
    	connect_func();
    	String sql = "Select DISTINCT email from User";
		resultSet = statement.executeQuery(sql);
		String email;
		List<String> users = new ArrayList<>();
        while (resultSet.next()) {
            email = resultSet.getString("email");
            users.add(email);
        }
        statement.close(); 
        resultSet.close();
        statement.close();
        disconnect();
        return users;
    }

	public List<String> getTopUsersWithPositiveReviews() throws SQLException {
		List<String> users = new ArrayList<String>();
		connect_func();
		String sql = "SELECT DISTINCT U.email FROM User AS U, Review AS R " + 
		"WHERE U.email = R.email AND U.email IN " + 
		"(SELECT DISTINCT R2.email FROM Review AS R2 WHERE R2.email NOT IN( " + 
		"SELECT DISTINCT R3.email from Review as R3 " + 
		"WHERE R3.score = 'poor' OR R3.score = 'fair'))";
		resultSet = statement.executeQuery(sql);
		User temp;    	
		String email;
		while (resultSet.next()) {
			email = resultSet.getString("email");
    		users.add(email);
		}
		statement.close();
	 	resultSet.close();
		disconnect();
		return users;
	}
	
	public List<String> getInactiveUsers() throws SQLException {
		List<String> users = new ArrayList<String>();
		connect_func();
		String sql = "SELECT * FROM User " +
		"WHERE email NOT IN (SELECT DISTINCT email FROM Video " +
		"UNION " +
		"SELECT DISTINCT email FROM Review " +
		"UNION " +
		"SELECT DISTINCT email From Question)";
		resultSet = statement.executeQuery(sql);
		String email;
		while (resultSet.next()) {
			email = resultSet.getString("email");
			users.add(email);
		}
		statement.close();
		resultSet.close();
		disconnect();
		return users;
    	}
}
