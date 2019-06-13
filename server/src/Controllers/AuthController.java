package src.Controllers;

import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;
import express.utils.Status;

import src.Data.User;
import src.Models.UserDAO;

/**
 * AuthController
 */
public class AuthController extends Controller {
	final UserDAO userDAO = new UserDAO();

	private class SignUpResponse {
		@SuppressWarnings("unused")
		public Boolean success = false;
	}

	@DynExpress(context = "/api/auth/signup", method = RequestMethod.POST)
	public void signup (Request req, Response res) {
		User user =  new User();
		User userPayload = new User();

		userPayload = parseBody(req, User.class);
		user = userDAO.search(userPayload);

		SignUpResponse result = new SignUpResponse();

		if (user != null && (user.getPassword() == null || user.getPassword().equals(""))) {
			if (userPayload.getPassword() == null || userPayload.getPassword().equals("")) {
				res.setStatus(Status._400).send();
				return;
			}
			user.setPassword(userPayload.getPassword());

			result.success = userDAO.update(user);
			res.send(gson.toJson(result));
			return;
		}

		result.success = userDAO.create(userPayload);

		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/api/auth/signin", method = RequestMethod.GET)
	public void signin (Request req, Response res) {
		if (!req.hasAuthorization()) {
			res.setStatus(Status._400).send();
			return;
		}
		
		// [0] = CPF | [1] = Password
		String[] credentials = req.getAuthorization()
			.get(0)
			.getDataBase64Decoded()
			.split(":");
		
		if (credentials.length != 2) {
			res.setStatus(Status._400).send();
			return;
		}

		User user = new User();
		user.setCpf(credentials[0]);
		user = userDAO.search(user);

		if (user == null) {
			res.setStatus(Status._400).send();
			return;
		}

		if (!user.getPassword().equals(credentials[1])) {
			res.setStatus(Status._400).send();
			return;
		}

		user.setPassword(null);
		res.send(gson.toJson(user));
	}
}
