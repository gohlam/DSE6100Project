public class Question {
	protected String questionID;
	protected String question;
	protected String email;
	protected String date;

	public Question() {
	}

	public Question(String questionID, String question, String email) {
		this.questionID = questionID;
		this.question = question;
		this.email = email;
	}
		
	public Question(String questionID, String question, String email, String date) {
		this(questionID, question, email);
		this.date = date;
	}
	
	
	public Question(String question, String questionID) {
		this.question = question;
		this.questionID = questionID;
	}
	
	public String getQuestionID() {
		return questionID;
	}
	
	public void setQuestionID(String questionID)  {
		this.questionID = questionID;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
}
