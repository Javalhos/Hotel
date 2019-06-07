package Data;

public class Rental {
	private int id;
	private String userCpf;
	private int room;
	private String entryDate;
	private String departureDate;
	private String status;
	private float value;

	public Rental(int id, String userCpf, int room, String entryDate, String departureDate, String status, float value) {
		this.id = id;
		this.userCpf = userCpf;
		this.room = room;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.status = status;
		this.value = value;
	}

	public void setInd(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setCpf(String cpf) {
		this.userCpf = cpf;
	}

	public String getCpf() {
		return userCpf;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getRoom() {
		return room;
	}

	public void setEntryDate(String date) {
		this.entryDate = date;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setDepartureDate(String date) {
		this.departureDate = date;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}
}