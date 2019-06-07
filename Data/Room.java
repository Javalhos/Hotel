package Data;

public class Room {
	private int beds;
	private int room;
	private String type;
	private String extensionPhone;
	private String status;
	private float dailyRate;

	public Room() {
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public int getBeds() {
		return beds;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getRoom() {
		return room;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setExtensionPhone(String extensionPhone) {
		this.extensionPhone = extensionPhone;
	}

	public String getExtensionPhone() {
		return extensionPhone;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDailyRate(float value) {
		this.dailyRate = value;
	}

	public float getDailyRate() {
		return dailyRate;
	}
}