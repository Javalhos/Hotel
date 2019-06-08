package Data;

public class Booking {
	private int id;
	private String userCpf;
	private int room;
	private String entryDate;
	private String departureDate;
	private float bookTax;
	private String status;
	private float value;

	public Booking() {
		
	}

	public Booking(String userCpf, int room, String entryDate, String departureDate, float bookTax, String status, float value) {
		this.userCpf = userCpf;
		this.room = room;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.bookTax = bookTax;
		this.status = status;
		this.value = value;
	}

	public void setId(int id) {
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

	public void setBookTax(float tax) {
		this.bookTax = tax;
	}

	public float getBookTax() {
		return bookTax;
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
