package Models;

import Data.Accomodation;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class AccomodationDAO implements DAO<Accomodation> {
	private String sql = "";

	public boolean create (Accomodation data) {
		sql = "INSERT INTO acoomodations" + 
			"(rental_id, booking_id, user_cpf, consumed_services, total_value, status)" +
			"VALUES (?, ?, ?, ?, ?, ?)";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getRentalId());
			pst.setInt(2, data.getBookingId());
			pst.setInt(3, data.getUserCpf());
			pst.setInt(4, data.getConsumedServices());
			pst.setFloat(5, data.getTotal());
			pst.setString(6, data.getStatus());
			
			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}

	public boolean update (Accomodation data) {
		sql = "UPDATE accomodations SET total_value = ?, status = ? WHERE id = ?";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setFloat(1, data.getTotal());
			pst.setString(2, data.getStatus());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
 		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}

	public boolean destroy (Accomodation data) {
		sql = "DELETE FROM accomodations WHERE id = ?";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getId());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}

	public Accomodation search (Accomodation data) {
		sql = "SELECT * FROM accomodations WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";

		Connect connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Accomodation) rs;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}

	public Collection<Accomodation> list (Accomodation where) {
		sql = "SELECT * FROM accomodations " + where;

		Connection connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Accomodation> accomodations = new ArrayList<>();
			while (rs.next()) {
				accomodations.add((Accomodation) rs);
			}

			return accomodations;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}