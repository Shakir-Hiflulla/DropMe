package customer;

public class Booking {
	
	protected int b_id;
	protected String name;
	protected String tel;
	protected String pickup_loc;
	protected String drop_loc;
	protected int distance;
	protected double cost;
	protected String date;
	protected String payment;
	protected int vid;
	protected int cid;
	protected String bk_status;
	protected int bk_did;
	
	

	public Booking(int b_id, String name, String tel, String pickup_loc, String drop_loc, int distance, double cost,
			String date, String payment, int vid, int cid, String bk_status, int bk_did) {
		this.b_id = b_id;
		this.name = name;
		this.tel = tel;
		this.pickup_loc = pickup_loc;
		this.drop_loc = drop_loc;
		this.distance = distance;
		this.cost = cost;
		this.date = date;
		this.payment = payment;
		this.vid = vid;
		this.cid = cid;
		this.bk_status = bk_status;
		this.bk_did = bk_did;
	}

	public String getBk_status() {
		return bk_status;
	}

	public int getBk_did() {
		return bk_did;
	}

	public int getB_id() {
		return b_id;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getPickup_loc() {
		return pickup_loc;
	}

	public String getDrop_loc() {
		return drop_loc;
	}

	public int getDistance() {
		return distance;
	}

	public Double getCost() {
		return cost;
	}

	public String getDate() {
		return date;
	}

	public String getPayment() {
		return payment;
	}

	public int getVid() {
		return vid;
	}

	public int getCid() {
		return cid;
	}
	

}
