package src.Controllers;

import java.util.*;
import express.DynExpress;
import express.http.RequestMethod;
import express.http.request.Request;
import express.http.response.Response;

import src.Data.Payment;
import src.Models.PaymentDAO;

public class PaymentController extends Controller {

	private final PaymentDAO paymentDAO = new PaymentDAO();
	private class CreatePaymentResponse {
		@SuppressWarnings("unused")
		public boolean success = false;
	}

	@DynExpress(context = "/payment/pendent")
	public void pendents (Request req, Response res) {
		Collection<Payment> payments = paymentDAO.list("WHERE status = 'PENDENTE'");

		res.send(gson.toJson(payments));
	}

	@DynExpress(context = "/payments")
	public void index(Request req, Response res) {
		Collection<Payment> payments = paymentDAO.list("");

		res.send(gson.toJson(payments));
	}

	@DynExpress(context = "/accpay/:acc")
	public void accpay (Request req, Response res) {
		int id = Integer.parseInt(req.getParam("acc"));
 		Collection<Payment> payments = paymentDAO.list("WHERE accomodation_id = '" + id +"' LIMIT 1");

		res.send(gson.toJson(payments));
	}

	// @DynExpress(context = "/payment/:id")
	// public void show (Request req, Response res) {
	// 	Payment payment = new Payment();
	// 	payment.setId(Integer.parseInt(req.getParam("id")));

	// 	Object pay = new Object();
	// 	pay = paymentDAO.search(payment);
	// 	res.send(gson.toJson(pay));
	// }

	@DynExpress(context = "/payment", method = RequestMethod.POST)
	public void store (Request req, Response res) {
		Payment payment = this.parseBody(req, Payment.class);

		CreatePaymentResponse result = new CreatePaymentResponse();

		result.success = paymentDAO.create(payment);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/payment", method = RequestMethod.PATCH)
	public void update (Request req, Response res) {
		Payment payment = this.parseBody(req, Payment.class);
		
		CreatePaymentResponse result = new CreatePaymentResponse();

		result.success = paymentDAO.update(payment);
		res.send(gson.toJson(result));
	}

	@DynExpress(context = "/payment/:id", method = RequestMethod.DELETE)
	public void delete (Request req, Response res) {
		Payment payment = new Payment();
		payment.setId(Integer.parseInt(req.getParam("id")));
		
		CreatePaymentResponse result = new CreatePaymentResponse();

		result.success = paymentDAO.destroy(payment);
		res.send(gson.toJson(result));
	}
}