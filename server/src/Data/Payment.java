package src.Data;

public class Payment {
	private int id;
	private int accId;
	private float tax;
	private float services;
	private float total;
	private float paid;
	private String type;
	private String status;

	public Payment() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setAccId(int id) {
		this.accId = id;
	}

	public int getAccId() {
		return accId;
	} 

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getTax() {
		return tax;
	}

	public void setService(float value) {
		this.services = value;
	}

	public float getService() {
		return services;
	}

	public void setPaid(float paid) {
		this.paid = paid;
	}

	public float getPaid() {
		return paid;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotal() {
		return total;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}