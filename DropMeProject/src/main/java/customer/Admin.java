package customer;

public class Admin extends User{
	private String status;

	public Admin(int id, String name, String email, String phone, String password, String status) {
		super(id, name, email, phone, password);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
