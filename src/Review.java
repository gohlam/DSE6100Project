
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

}
