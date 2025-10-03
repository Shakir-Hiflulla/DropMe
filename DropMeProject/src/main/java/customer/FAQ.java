package customer;

public class FAQ {
	int id;
	private String question;
	private String reply;
	private String email;
	private String status;
	
	public FAQ(int id,String question, String reply, String email, String status) {
		
		this.id=id;
		this.question = question;
		this.reply = reply;
		this.email = email;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getReply() {
		return reply;
	}

	public String getEmail() {
		return email;
	}

	public String getpStatus() {
		return status;
	}
	
	
	

}
