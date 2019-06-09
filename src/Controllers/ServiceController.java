package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.Service;
import src.Models.ServiceDAO;

public class ServiceController extends Controller {

	private final ServiceDAO serviceDAO = new ServiceDAO();
	private class CreateServiceResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	@DynExpress(context = "/services")
	public void index (Request req, Response res) {
		Collection<Service> services = serviceDAO.list("");

		res.send(gson.toJson(services));
	}

	@DynExpress(context = "/service/:id")
	public void show (Request req, Response res) {
		Service service = new Service();
		service.setId(Integer.parseInt(req.getParam("id")));

		Object srv = new Object();
		srv = serviceDAO.search(service);
		res.send(gson.toJson(srv));
	}

	@DynExpress(context = "/service", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Service service = this.parseBody(req, Service.class);

		CreateServiceResponse result = new CreateServiceResponse();

		result.success = serviceDAO.create(service);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/service", method = RequestMethod.PATCH)
	public void edit (Request req, Response res) {
		Service service = this.parseBody(req, Service.class);

		CreateServiceResponse result = new CreateServiceResponse();

		result.success = serviceDAO.update(service);
		res.send(gson.toJson(result));
	}
}