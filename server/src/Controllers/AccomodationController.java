package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.Accomodation;
import src.Models.AccomodationDAO;

public class AccomodationController extends Controller {

	private final AccomodationDAO accomodationDAO = new AccomodationDAO();
	private class CreateAccomodationResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	@DynExpress(context = "/accomodations")
	public void index (Request req, Response res) {
		Collection<Accomodation> accomodations = accomodationDAO.list("");

		res.send(gson.toJson(accomodations));
	}

	@DynExpress(context = "/accomodation/:id")
	public void show (Request req, Response res) {
		Accomodation accomodation = new Accomodation();
		accomodation.setId(Integer.parseInt(req.getParam("id")));

		Object acc = new Object();
		acc = accomodationDAO.search(accomodation);
		res.send(gson.toJson(acc));
	}

	@DynExpress(context = "/accomodation", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Accomodation accomodation = this.parseBody(req, Accomodation.class);

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.create(accomodation);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/accomodation", method = RequestMethod.PATCH)
	public void update (Request req, Response res) {
		Accomodation accomodation = this.parseBody(req, Accomodation.class);

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.update(accomodation);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/accomodation/:id", method = RequestMethod.DELETE)
	public void destroy (Request req, Response res) {
		Accomodation accomodation = new Accomodation();
		accomodation.setId(Integer.parseInt(req.getParam("id")));

		CreateAccomodationResponse result = new CreateAccomodationResponse();

		result.success = accomodationDAO.destroy(accomodation);
		res.send(gson.toJson(result));
	}
}