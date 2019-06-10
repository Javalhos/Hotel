

package src.Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import express.http.request.Request;

/**
 * Controller
 */
public abstract class Controller {
	
	protected final Gson gson = new Gson();

	protected <T> T parseBody (Request req, Class<T> tClass) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getBody()));
		try {
			return gson.fromJson(reader, tClass);
		} catch (JsonSyntaxException | JsonIOException e) {
			return null;
		}
	}
}