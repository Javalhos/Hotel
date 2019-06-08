import express.Express;
import express.middleware.Middleware;
import java.io.IOException;

// Importar os Controllers
import src.Controllers.*;
/**
 import src.Controllers.RoomController;
 * Server
 */
public class Server {
	static final int PORT = 80;
	public static void main(String[] args) throws IOException {
		Express app = new Express();

		app.use(Middleware.statics("./client/dist"));
		
		// Room Controller
		app.bind(new RoomController());

		app.listen(PORT);
		System.out.println("Server running on port " + PORT + "...");
	}
}
