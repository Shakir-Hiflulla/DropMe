package customer;

public class Driver extends User{
	private String licenseNum;
	private Vehicle veh;
	
	public Vehicle getVeh() {
		return veh;
	}

	public Driver(int id, String name, String email, String phone, String password, String licenseNum, Vehicle veh) {
		super(id, name, email, phone, password);
		this.licenseNum = licenseNum;
		this.veh = veh;
	}

	public Driver(int id, String name, String email, String phone, String password, String licenseNum) {
		super(id, name, email, phone, password);
		this.licenseNum = licenseNum;

	}

	public String getLicenseNum() {
		return licenseNum;
	}

}
