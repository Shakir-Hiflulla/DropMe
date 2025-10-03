package customer;

public class Vehicle {
	private int id;
	private String name;
	private String number;
	private double rate;
	private String brand;
	private String type;
	private String status;
	private int driverId;
	
	
	public int getDriverId() {
		return driverId;
	}

	public Vehicle(int id, String name, String number, double rate, String brand, String type,String status, int driverId) {

		this.id = id;
		this.name = name;
		this.number = number;
		this.rate = rate;
		this.brand = brand;
		this.type = type;
		this.status=status;
		this.driverId = driverId;
	}

	public String getStatus() {
		return status;
	}

	public String getBrand() {
		return brand;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public double getRate() {
		return rate;
	}
	
	
	
	
	
}
