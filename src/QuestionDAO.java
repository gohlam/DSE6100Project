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

@WebServlet("/QuestionDAO")
public class QuestionDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	

	public QuestionDAO() {
		
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
    
    public List<Question> getQuestions() throws SQLException {
    	connect_func();
    	String sql = "Select questionID, question from Question;";
    	resultSet = statement.executeQuery(sql);
    	int qid;
    	String question;
    	Question temp;
    	List<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
        	qid = resultSet.getInt("questionID");
        	question = resultSet.getString("question");
        	temp = new Question(question, qid);
        	questions.add(temp);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
    	return questions;
    }


	public void addQuestion(String email, String question, String tag) throws SQLException {
    	connect_func();         
	String sql = "insert into Question(email, question, tag) values (?, ?, ?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, email);
	preparedStatement.setString(2, question);
	preparedStatement.setString(3, tag);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public void removeQuestion(String email, String question, String tag) throws SQLException {
    	connect_func();         
	String sql = "DELETE FROM Questions WHERE email = ? AND question = ? AND tag = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, email);
	preparedStatement.setString(2, question);
	preparedStatement.setString(3, tag);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public Question getQuestion(String email, String question, String tag) throws SQLException {
    	connect_func();         
		String sql = "Select * FROM Question where email = ? AND url = ? AND tag =?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, question);
		preparedStatement.setString(3, tag);

        resultSet = preparedStatement.executeQuery();
        Question temp = new Question();
        while (resultSet.next()) {
        	temp.setQuestionID(resultSet.getInt("questionID"));
        	temp.setEmail(resultSet.getString("email"));
            temp.setQuestion(resultSet.getString("question"));
            temp.setTag(resultSet.getString("tag"));
            temp.setDate(resultSet.getString("date"));
        }
        preparedStatement.close();
        statement.close();
        resultSet.close();
        disconnect();
        return temp;
    	
    }

    public List<String> usersQuestion(String email) throws SQLException {
    	connect_func();         
		String sql = "Select questionid FROM Question where email = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		
        resultSet = preparedStatement.executeQuery();
        List<String> Question = new ArrayList<String>();
        while (resultSet.next()) {
            Question.add(resultSet.getString("questionid"));
        }
        preparedStatement.close();
        statement.close();
        disconnect();
        return Question;
        }
}
