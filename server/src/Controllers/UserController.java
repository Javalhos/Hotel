package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.User;
import src.Models.UserDAO;

/**
 * UserController
 */
public class UserController extends Controller {
	private final UserDAO userDAO = new UserDAO();
	private class CreateUserResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	@DynExpress(context = "/users")
	public void index (Request req, Response res) {
		Collection<User> users = userDAO.list("");

		res.send(gson.toJson(users));
	}

	@DynExpress(context = "/user/:cpf")
	public void show (Request req, Response res) {
		User user = new User();
		user.setCpf(req.getParam("cpf"));

		Object usr = new Object();
		usr = userDAO.search(user);
		res.send(gson.toJson(usr));
	}
}
