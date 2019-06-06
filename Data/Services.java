package Data;

public class Services {
	private int id;
	private String name;
	private String description;
	private float value;

	public Services(int id, String name, String description, float value) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.value = value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}
}