package customer;

public class Payment {
	private int pay_id;
	private double amount;
	private String cNumber;
	private String payment_date;
	private String payment_discription;
	private int cid;
	
	public Payment(int pay_id, double amount, String cNumber, String payment_date, String payment_discription,
			int cid) {
		super();
		this.pay_id = pay_id;
		this.amount = amount;
		this.cNumber = cNumber;
		this.payment_date = payment_date;
		this.payment_discription = payment_discription;
		this.cid = cid;
	}

	public int getPay_id() {
		return pay_id;
	}

	public double getAmount() {
		return amount;
	}

	public String getcNumber() {
		return cNumber;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public String getPayment_discription() {
		return payment_discription;
	}

	public int getCid() {
		return cid;
	}
}
