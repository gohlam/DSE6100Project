
public class Video {
	protected String url;
	protected String title;
	protected String description;
	protected int qid;
	protected String email;
	protected String date;
	
	public Video() {
	}

	public Video(String url, String title, String description, int qid, String email) {
		this.url = url;
		this.title = title;
		this.description = description;
		this.qid = qid;
		this.email = email;
	}
		
	public Video(String url, String title, String description, int qid, String email, String date) {
		this(url, title, description, qid, email);
		this.date = date;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url)  {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setQID(int qid) {
		this.qid = qid;
	}
	
	public int getQID() {
		return qid;
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
