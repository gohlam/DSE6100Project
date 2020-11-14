public class Question {
	protected int questionID;
	protected String question;
	protected String email;
	protected String tag;
	protected String date;

	public Question() {
	}

	public Question(int questionID, String question, String email, String tag) {
		this.questionID = questionID;
		this.question = question;
		this.email = email;
		this.tag = tag;
	}
		
	public Question(int questionID, String question, String email, String tag, String date) {
		this(questionID, question, email, tag);
		this.date = date;
	}
	
	
	public Question(String question, int questionID) {
		this.question = question;
		this.questionID = questionID;
	}
	
	public int getQuestionID() {
		return questionID;
	}
	
	public void setQuestionID(int questionID)  {
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
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
}
