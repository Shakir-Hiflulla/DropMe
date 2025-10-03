package customer;

public class CusBooking extends Booking {
	private String veh_num;
	private String dri_number;
	private String dri_name;
	
	
	public CusBooking(int b_id, String name, String tel, String pickup_loc, String drop_loc, int distance, double cost,
			String date, String payment, int vid, int cid, String bk_status, int bk_did, String veh_num,
			String dri_number, String dri_name) {
		super(b_id, name, tel, pickup_loc, drop_loc, distance, cost, date, payment, vid, cid, bk_status, bk_did);
		this.veh_num = veh_num;
		this.dri_number = dri_number;
		this.dri_name = dri_name;
	}


	public String getVeh_num() {
		return veh_num;
	}


	public String getDri_number() {
		return dri_number;
	}


	public String getDri_name() {
		return dri_name;
	}
	
	

}
