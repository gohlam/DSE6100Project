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
    	String qid;
    	String question;
    	Question temp;
    	List<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
        	qid = resultSet.getString("questionID");
        	question = resultSet.getString("question");
        	temp = new Question(question, qid);
        	questions.add(temp);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
    	return questions;
    }


	public void addQuestion(String email, String question) throws SQLException {
    	connect_func();         
		String sql = "insert into Question(email, question, date) values (?, ?, CURDATE())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, question);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public void removeQuestion(String email, int questionID) throws SQLException {
    	connect_func();         
		String sql = "DELETE FROM Questions WHERE email = ? AND questionID = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setInt(2, questionID);
		
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
        disconnect();    	
    }
    
    public Question getQuestion(String email, String question, String tag) throws SQLException {
    	connect_func();         
		String sql = "Select * FROM Question where email = ? AND url = ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, question);

        resultSet = preparedStatement.executeQuery();
        Question temp = new Question();
        while (resultSet.next()) {
        	temp.setQuestionID(resultSet.getString("questionID"));
        	temp.setEmail(resultSet.getString("email"));
            temp.setQuestion(resultSet.getString("question"));
            temp.setDate(resultSet.getString("date"));
        }
        preparedStatement.close();
        statement.close();
        resultSet.close();
        disconnect();
        return temp;
    	
    }
    
    public List<Question> getQuestionsWithPoorVideos() throws SQLException {
    	connect_func();         
    	List<Question> questions = new ArrayList<Question>();
    	String sql = "SELECT * FROM Question as Q, Video as V, Review as R " + 
    			"WHERE Q.questionID = V.qid AND R.URL = V.URL " + 
    			"AND R.score = 'poor' AND Q.questionID NOT IN ( " + 
    			"SELECT DISTINCT V2.qid from Video as V2, Review as R " + 
    			"WHERE (R.score = 'excellent' OR R.score = 'fair' OR R.score = 'good') AND V2.URL = R.URL)";
    	resultSet = statement.executeQuery(sql);
    	String qid;
    	String question;
    	Question temp;
        while (resultSet.next()) {
        	qid = resultSet.getString("questionID");
        	question = resultSet.getString("question");
        	temp = new Question(question, qid);
        	questions.add(temp);
        }
    	statement.close();
        resultSet.close();
        disconnect();
    	return questions;
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
        resultSet.close();
        disconnect();
        return Question;
        }
    
    public List<String> getPopularTags() throws SQLException {
    	connect_func();         
    	List<String> tags = new ArrayList<String>();
    	String sql = "SELECT T.tag FROM Tag as T, Question as Q WHERE T.questionID = Q.questionID " +
    			"GROUP BY T.tag HAVING COUNT(Q.email) > ((SELECT COUNT(*) AS numUsers FROM User)/2) - 1";
    	resultSet = statement.executeQuery(sql);
    	String tag;
    	while (resultSet.next()) {
    		tag = resultSet.getString("T.tag");
    		System.out.println(tag);
    		tags.add(tag);
    	}
    	statement.close();
        resultSet.close();
        disconnect();
    	return tags;
    }
	
	public List<Question> getNewQuestionsToday() throws SQLException {
		List<Question> questions = new ArrayList<>();
		connect_func();
		String sql = "SELECT * FROM Question WHERE date = CURDATE()";
		resultSet = statement.executeQuery(sql);
		Question temp;
		String questionID;
		String question;
		String date;
		String email;
		while (resultSet.next()) {
			questionID = resultSet.getString("questionID");
			question = resultSet.getString("question");
			date = resultSet.getString("postdate");
			email = resultSet.getString("email");
			temp = new Question(questionID, question, email, date);
			questions.add(temp);
		}
		resultSet.close();
	   	statement.close();
	    disconnect();
		return questions;
	}
	
	public List<Question> getTopQuestionsWithMostVideos() throws SQLException {
		List<Question> questions = new ArrayList<>();
		connect_func();
		String sql = "SELECT Q.questionID, Q.question " +
				"FROM Question AS Q, Video AS V WHERE Q.URL = V.URL GROUP BY Q.questionID ORDER BY COUNT(*) DESC";
		resultSet = statement.executeQuery(sql);
		Question temp;
		String questionID;
		String question;
		while (resultSet.next()) {
       		questionID = resultSet.getString("questionID");
       		question = resultSet.getString("question");
    		temp = new Question(questionID, question);
    		questions.add(temp);
		}
		resultSet.close();
	   	statement.close();
	    disconnect();
		return questions;
	}
	
}
