package Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import com.google.gson.Gson;
import express.http.request.Request;

import Data.Room;
import DAO.RoomDAO;

public class RoomController {
	public static String index() {
		Gson gson = new Gson();
		RoomDAO dao = new RoomDAO();

		Collection<Room> rooms = dao.list("");
		return gson.toJson(rooms);
	}

	public static String search(Request req) {
		Gson gson = new Gson();
		RoomDAO dao = new RoomDAO();

		Room room = new Room();
		room.setRoom(Integer.parseInt(req.getParam("id")));

		Object rm = new Object();
		try {
			rm = dao.search(room);
			return gson.toJson(rm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String create(Request req) {
		Gson gson = new Gson();
		String data = req.getBody().toString();
		System.out.println(data);
		// RoomDAO dao = new RoomDAO();

		// Room room = new Room();

		try {
			
			// System.out.println(gson.toJson(req.getBody()));
			return "É";
		} catch (Exception e) {
			return "Erro";
		}
	}
}