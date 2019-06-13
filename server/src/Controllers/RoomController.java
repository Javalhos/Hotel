package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;
import express.utils.Status;

import src.Data.Accomodation;
import src.Data.Room;
import src.Models.AccomodationDAO;
import src.Models.RoomDAO;

public class RoomController extends Controller {

	private final RoomDAO roomDAO = new RoomDAO();
	private final AccomodationDAO acmdDAO = new AccomodationDAO();

	private class CreateRoomResponse {
		@SuppressWarnings("unused")
		public Boolean success = false;
	}

	@DynExpress(context = "/api/rooms")
	public void all (Request req, Response res) {
		Collection<Room> rooms = roomDAO.list(" WHERE status = 'DISPONIVEL'");

		res.send(gson.toJson(rooms));
	}

	@DynExpress(context = "/api/rooms/:id")
	public void single (Request req, Response res) {
		Collection<Accomodation> accomodations = new ArrayList<Accomodation>();
		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		room = roomDAO.search(room);

		if (room == null) {
			res.setStatus(Status._404).send();
			return;
		}

		if (!room.getStatus().equals("DISPONIVEL")) {
			res.setStatus(Status._404).send();
			return;
		}

		// Carregar as reservas desse quarto
		String clause = " WHERE `room` = %d AND `type` = '%s' AND `status` = '%s' AND `departure_date` >= CURDATE()";
		clause = String.format(clause, room.getRoom(), "RESERVA", "DISPONIVEL");
		accomodations = acmdDAO.list(clause);

		if (accomodations != null) {
			for (Accomodation acmd : accomodations) {
				// remove o cpf dos usuários que fizeram as reservas
				acmd.setCpf(null);
			}
		}

		room.setAccomodations(accomodations);

		res.send(gson.toJson(room));
	}

	@DynExpress(context = "/api/room")
	public void index (Request req, Response res) {
		Collection<Room> rooms = roomDAO.list("");

		res.send(gson.toJson(rooms));
	}

	@DynExpress(context = "/api/room/:id")
	public void show (Request req, Response res) {
		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		room = roomDAO.search(room);
		res.send(gson.toJson(room));
	}

	@DynExpress(context = "/api/room", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Room room = this.parseBody(req, Room.class);

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.create(room);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/api/room/:id", method = RequestMethod.PATCH)
	public void update (Request req, Response res) {
		Room room = this.parseBody(req, Room.class);

		int id = Integer.parseInt(req.getParam("id"));

		room.setRoom(id);

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.update(room);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/api/room/:id", method = RequestMethod.DELETE)
	public void destroy (Request req, Response res) {
		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.destroy(room);
		res.send(gson.toJson(result));
	}
}
