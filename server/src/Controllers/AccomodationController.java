package src.Controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;
import express.utils.Status;
import src.Data.Accomodation;
import src.Data.Room;
import src.Data.User;
import src.Models.AccomodationDAO;
import src.Models.RoomDAO;
import src.Models.UserDAO;

public class AccomodationController extends Controller {

	private final AccomodationDAO accomodationDAO = new AccomodationDAO();
	private final RoomDAO roomDAO = new RoomDAO();
	private final UserDAO userDAO = new UserDAO();

	private class CreateAccomodationResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	// Customer
	@DynExpress(context = "/api/reservar", method = RequestMethod.POST)
	public void reserve (Request req, Response res) {
		Accomodation accomodation = parseBody(req, Accomodation.class);

		Room room = new Room();
		room.setRoom(accomodation.getRoom());
		
		room = roomDAO.search(room);

		if (room == null) {
			res.setStatus(Status._404).send();
			return;
		}

		// VErifica se o quarto está disponível
		String clause = "WHERE `room` = %d AND (`entry_date` BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)) OR (`departure_date` BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE))";
		clause = String.format(clause, room.getRoom(), accomodation.getEntryDate(), accomodation.getDepartureDate(), accomodation.getEntryDate(), accomodation.getDepartureDate());
		Collection<Accomodation> accs = accomodationDAO.list(clause);

		if (accs != null && !accs.isEmpty()) {
			res.setStatus(Status._400).send();
			return;
		}

		User user = new User();
		user.setCpf(accomodation.getCpf());
		user = userDAO.search(user);

		if (user == null) {
			res.setStatus(Status._404).send();
			return;
		}

		LocalDate checkin = LocalDate.parse(accomodation.getEntryDate());
		LocalDate checkout = LocalDate.parse(accomodation.getDepartureDate());

		long days = ChronoUnit.DAYS.between(checkin, checkout);

		float value = days * room.getDailyRate();

		accomodation.setAccType("RESERVA");
		accomodation.setStatus("PENDENTE");
		accomodation.setAccValue(value);

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.create(accomodation);
		res.send(gson.toJson(result));
	}

	// Staff
	@DynExpress(context = "/accomodations")
	public void index (Request req, Response res) {
		Collection<Accomodation> accomodations = accomodationDAO.list("");

		res.send(gson.toJson(accomodations));
	}

	@DynExpress(context = "/booking/:cpf")
	public void getBooking (Request req, Response res) {
		String cpf = req.getParam("cpf");
		Collection<Accomodation> accomodation = accomodationDAO.list("WHERE type = 'RESERVA' AND " +
		"cpf = '" + cpf + "' LIMIT 1");

		res.send(gson.toJson(accomodation));
	}

	@DynExpress(context = "/accomodation/:cpf/:id")
	public void show (Request req, Response res) {
		Accomodation accomodation = new Accomodation();

		if (req.getParam("cpf") != null) {
			accomodation.setCpf(req.getParam("cpf"));
		}

		if (req.getParam("id") != "0") {
			accomodation.setId(Integer.parseInt(req.getParam("id")));
		}

		Object acc = new Object();
		acc = accomodationDAO.search(accomodation);
		System.out.println(acc);
		res.send(gson.toJson(acc));
	}

	@DynExpress(context = "/accomodation", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Accomodation accomodation = this.parseBody(req, Accomodation.class);

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.create(accomodation);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/accomodation", method = RequestMethod.PATCH)
	public void update (Request req, Response res) {
		Accomodation accomodation = this.parseBody(req, Accomodation.class);

		CreateAccomodationResponse result = new CreateAccomodationResponse();
		
		result.success = accomodationDAO.update(accomodation);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/accomodation/:id", method = RequestMethod.DELETE)
	public void destroy (Request req, Response res) {
		Accomodation accomodation = new Accomodation();
		accomodation.setId(Integer.parseInt(req.getParam("id")));

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.destroy(accomodation);
		res.send(gson.toJson(result));
	}
}