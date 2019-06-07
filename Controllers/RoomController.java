package Controllers;

import java.util.*;
import com.google.gson.Gson;
import Data.Room;
import Models.RoomDAO;

public class RoomController {
	public static String index () {
		Gson gson = new Gson();
		RoomDAO dao = new RoomDAO();
		Collection<Room> rooms = dao.list("");

		return gson.toJson(rooms);
	}
}
