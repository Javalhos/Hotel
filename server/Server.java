import express.Express;
import express.middleware.Middleware;
import java.io.IOException;

// Importar os Controllers
import src.Controllers.*;

/**
 * Server
 */
public class Server {
	static final int PORT = 80;
	public static void main(String[] args) throws IOException {
		Express app = new Express();

		// Set Middlewares
		app.use(Middleware.statics("../client/dist")); // Static Files Middleware
		app.use((req, res) -> res.setContentType("application/json"));
		
		// Auth Controller
		app.bind(new AuthController());

		// Room Controller
		app.bind(new RoomController());
		
		// Payment Controller
		app.bind(new PaymentController());
		
		// Service Controller
		app.bind(new ServiceController());

		// Consumed Controller
		app.bind(new ConsumedController());

		// Accomodations Controller
		app.bind(new AccomodationController());

		// User Controller
		app.bind(new UserController());

		app.listen(PORT);
		System.out.println("Server running on port " + PORT + "...");
	}
}
