package Data;

public class Accomodation {
	private int id;
	private int rentalId; // Id do Aluguel correspondente, se uma reserva existir, este será 0.
	private int bookingId; // Id da Reserva correspondente, se uma reserva existir, este será 0.
	private String userCpf;
	private int consumedServices;
	private float totalValue;
	private String status;

	public Accomodation(int id, int rental, int booking, String cpf, int consumedServices, float total, String status) {
		this.id = id;
		this.rentalId = rental;
		this.bookingId = booking;
		this.userCpf = cpf;
		this.consumedServices = consumedServices;
		this.totalValue = total;
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRentalId(int id) {
		this.rentalId = id;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setBookingId(int id) {
		this.bookingId = id;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setUserCpf(String cpf) {
		this.userCpf = cpf;
	}

	public String getUserCpf() {
		return userCpf;
	}

	public void setConsumedServices(int id) {
		this.consumedServices = id;
	}

	public int getConsumedServices() {
		return consumedServices;
	}

	public void setTotal(float value) {
		this.totalValue = value;
	}

	public float getTotal() {
		return totalValue;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}