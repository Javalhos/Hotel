import express.Express;
import express.middleware.Middleware;
import com.google.gson.Gson;
import java.util.*;
import java.io.IOException;
import java.lang.*;

import Controllers.RoomController;
/**
 * Server
 */
public class Server {

	public static void main(String[] args) throws IOException {
		Express app = new Express();

		Gson gson = new Gson();
		app.get("/room", (req, res) -> {
			res.send(RoomController.index());
		});
		app.use(Middleware.statics("./app/dist"));
		app.listen();
	}
}
