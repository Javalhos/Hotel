package Data;

public class ConsumedServices {
	private int id;
	private int accomodationId;
	// Será criada uma nova linha para cada serviço consumido e
	// estes serão somados no final.
	private int serviceId;
	private String name;
	private float value;

	public ConsumedServices(int id, int accId, int serviceId, String name, float value) {
		this.id = id;
		this.accomodationId = accId;
		this.serviceId = serviceId;
		this.name = name;
		this.value = value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setAccomodation(int id) {
		this.accomodationId = id;
	}

	public int getAccomodation() {
		return accomodationId;
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