
public class Question {
	protected String question;
	protected Integer qid;
	
	public Question() {
		
	}
	
	public Question(String question, Integer qid) {
		this.question = question;
		this.qid = qid;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Integer getQid() {
		return qid;
	}
	
	public void setQid(Integer qid) {
		this.qid = qid;
	}
}
