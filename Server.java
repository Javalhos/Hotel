import express.Express;
import express.middleware.Middleware;
import com.google.gson.Gson;
import java.util.*;
import java.io.IOException;
import java.lang.*;

import Controllers.RoomController;
import Controllers.UserController;

/**
 * Server
 */
public class Server {
	public static void main(String[] args) throws IOException {
		Express app = new Express();

		Gson gson = new Gson();

		// Users End Points
		app.get("/users", (req, res) -> {
			res.send(UserController.index());
		});

		// Rooms End Points
		app.get("/rooms", (req, res) -> {
			res.send(RoomController.index());
		});

		app.get("/room/:id", (req, res) -> {
			res.send(RoomController.search(req));
		});

		app.post("/room", (req, res) -> {
			System.out.println(req);
			res.send(RoomController.create(req));
		});


		app.use(Middleware.statics("./app/dist"));
		app.listen();
	}
}
