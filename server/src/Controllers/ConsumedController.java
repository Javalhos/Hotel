package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.Consumed;
import src.Models.ConsumedDAO;

public class ConsumedController extends Controller {

	private final ConsumedDAO consumedDAO = new ConsumedDAO();
	private class CreateConsumedResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	@DynExpress(context = "/consumeds")
	public void index (Request req, Response res) {
		Collection<Consumed> consumed = consumedDAO.list("");

		res.send(gson.toJson(consumed));
	}

	@DynExpress(context = "/consumed/:id")
	public void show (Request req, Response res) {
		Consumed consumed = new Consumed();
		consumed.setId(Integer.parseInt(req.getParam("id")));

		Object csm = new Object();
		csm = consumedDAO.search(consumed);
		res.send(gson.toJson(csm));
	}

	@DynExpress(context = "/consumed", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Consumed consumed =this.parseBody(req, Consumed.class);

		CreateConsumedResponse result = new CreateConsumedResponse();

		result.success = consumedDAO.create(consumed);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/consumed", method = RequestMethod.PATCH)
	public void edit (Request req, Response res) {
		Consumed consumed = this.parseBody(req, Consumed.class);

		CreateConsumedResponse result = new CreateConsumedResponse();

		result.success = consumedDAO.update(consumed);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/consumed/:id", method = RequestMethod.DELETE)
	public void destroy (Request req, Response res) {
		Consumed consumed = new Consumed();
		consumed.setId(Integer.parseInt(req.getParam("id")));

		CreateConsumedResponse result = new CreateConsumedResponse();

		result.success = consumedDAO.destroy(consumed);
		res.send(gson.toJson(result));
	}
}