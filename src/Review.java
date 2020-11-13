
public class Review {
	protected String comment;
	protected String score;
	protected String email;
	protected String url;
	protected String reviewDate;
	
	public Review() {
	}
	
	public Review(String comment, String score, String email, String url) {
		this.comment = comment;
		this.score = score;
		this.email = email;
		this.url = url;
	}
	
	public Review(String comment, String score, String email, String url, String reviewDate) {
		this(comment, score, email, url);
		this.reviewDate = reviewDate;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment)  {
		this.comment = comment;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getURL() {
		return url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public String getDate() {
		return reviewDate;
	}
	
	public void setDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
}
