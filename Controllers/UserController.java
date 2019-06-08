package Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import com.google.gson.Gson;
import express.http.request.Request;

import Data.User;
import DAO.UserDAO;

public class UserController {
	public static String index() {
		Gson gson = new Gson();
		UserDAO dao = new UserDAO();

		Collection<User> users = dao.list("");
		return gson.toJson(users);
	}
}