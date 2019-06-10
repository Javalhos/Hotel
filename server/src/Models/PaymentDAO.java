package src.Models;

// Java & 3rd Party
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

// Project
import src.Data.Payment;
import src.Database.DB;

public class PaymentDAO implements DAO<Payment> {
	private String sql = "";

	public boolean create (Payment data) {
		sql = "INSERT INTO `payment` " +
		"(accomodation_id, tax, services_value, total_value, payment_type, status) VALUES " +
		"(?, ?, ?, ?, ?, ?)";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);
			
			pst.setInt(1, data.getAccId());
			pst.setFloat(2, data.getTax());
			pst.setFloat(3, data.getService());
			pst.setFloat(4, data.getTotal());
			pst.setString(5, data.getType());
			pst.setString(6, data.getStatus());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Payment data) {
		sql = "UPDATE `payment` SET tax = ?, services_value = ?, " +
		"total_value = ?, payment_type = ?, status = ? " +
		"WHERE id = '" + data.getId() + "'";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setFloat(1, data.getTax());
			pst.setFloat(2, data.getService());
			pst.setFloat(3, data.getTotal());
			pst.setString(4, data.getType());
			pst.setString(5, data.getStatus());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean destroy (Payment data) {
		sql = "DELETE FROM `payment` WHERE id = ?";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getId());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Payment search (Payment data) {
		sql = "SELECT * FROM `payment` WHERE id = '" + data.getId() + "'";

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Payment payment = new Payment();
			while(rs.next()) {
				payment.setId(rs.getInt("id"));
				payment.setAccId(rs.getInt("accomodation_id"));
				payment.setTax(rs.getFloat("tax"));
				payment.setService(rs.getFloat("services_value"));
				payment.setTotal(rs.getFloat("total_value"));
				payment.setType(rs.getString("payment_type"));
				payment.setStatus(rs.getString("status"));
			}

			return payment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Payment> list (String where) {
		sql = "SELECT * FROM `payment` " + where;

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Payment> payments = new ArrayList<Payment>();
			while(rs.next()) {
				Payment payment = new Payment();

				payment.setId(rs.getInt("id"));
				payment.setAccId(rs.getInt("accomodation_id"));
				payment.setTax(rs.getFloat("tax"));
				payment.setService(rs.getFloat("services_value"));
				payment.setTotal(rs.getFloat("total_value"));
				payment.setType(rs.getString("payment_type"));
				payment.setStatus(rs.getString("status"));
				payments.add(payment);
			}

			return payments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}