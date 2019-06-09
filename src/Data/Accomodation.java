package Data;

public class Accomodation {
	private int id;
	private int room;
	private String cpf;
	private String accomodationType;
	private String entryDate;
	private String departureDate;
	private String status;
	private float accValue;

	public Accomodation() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getRoom() {
		return room;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setAccType(String type) {
		this.accomodationType = type;
	}

	public String getAccType() {
		return accomodationType;
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

	public void setAccValue(float value) {
		this.accValue = value;
	}

	public float getAccValue() {
		return accValue;
	}
}