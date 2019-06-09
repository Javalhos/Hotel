package Data;

public class Consumed {
	private int id;
	private int accId;
	private int serviceId;
	private String name;
	private float value;

	public Consumed() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setAccomodation(int id) {
		this.accId = id;
	}

	public int getAccomodation() {
		return accId;
	}

	public void setServiceId(int id) {
		this.serviceId = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceName(String name) {
		this.name = name;
	}

	public String getServiceName() {
		return name;
	}

	public void setServiceValue(float value) {
		this.value = value;
	}

	public float getServiceValue() {
		return value;
	}
}