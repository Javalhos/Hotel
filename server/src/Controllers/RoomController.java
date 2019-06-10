package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.Room;
import src.Models.RoomDAO;

public class RoomController extends Controller {

	private final RoomDAO roomDAO = new RoomDAO();
	private class CreateRoomResponse {
		@SuppressWarnings("unused")
		public Boolean success = false;
	}

	@DynExpress(context = "/rooms")
	public void index (Request req, Response res) {
		Collection<Room> rooms = roomDAO.list("");

		res.send(gson.toJson(rooms));
	}

	@DynExpress(context = "/room/:id")
	public void show (Request req, Response res) {
		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		Object rm = new Object();
		rm = roomDAO.search(room);
		res.send(gson.toJson(rm));
	}

	@DynExpress(context = "/room", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Room room = this.parseBody(req, Room.class);

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.create(room);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/room", method = RequestMethod.PATCH)
	public void edit (Request req, Response res) {
		Room room = this.parseBody(req, Room.class);

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.update(room);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/room/:id", method = RequestMethod.DELETE)
	public void destroy (Request req, Response res) {
		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		CreateRoomResponse result = new CreateRoomResponse();

		result.success = roomDAO.destroy(room);
		res.send(gson.toJson(result));
	}
}
