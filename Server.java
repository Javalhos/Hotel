import express.Express;
import express.middleware.Middleware;
import java.io.IOException;

import src.Controllers.RoomController;
/**
 * Server
 */
public class Server {

	public static void main(String[] args) throws IOException {
		Express app = new Express();

		app.use(Middleware.statics("./client/dist"));
		app.get("/room", (req, res) -> {
			res.send(RoomController.index());
		});
		app.listen(8080);
		System.out.println("Server running on port 8080...");
	}
}
