package Models;

import Data.Rental;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class RentalDAO implements DAO<Rental> {
	private String sql = "";

	public boolean create (Rental data) {
		sql = "INSERT INTO rentals" + 
			"(user_cpf, room, entry_date, departure_date, status, value)" +
			"VALUES (?, ?, ?, ?, ?, ?)";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getCpf());
			pst.setInt(2, data.getRoom());
			pst.setString(3, data.getEntryDate());
			pst.setString(4, data.getDepartureDate());
			pst.setString(5, data.getStatus());
			pst.setFloat(6, data.getValue());
			
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

	public boolean update (Rental data) {
		sql = "UPDATE rentals SET user_cpf = ?, room = ?, entry_date = ?, departure_data = ?, "
				+ "status = ?, value = ? WHERE id = ?";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setInt(1, data.getCpf());
			pst.setInt(2, data.getRoom());
			pst.setString(3, data.getEntryDate());
			pst.setString(4, data.getDepartureDate());
			pst.setString(5, data.getStatus());
			pst.setFloat(6, data.getValue());

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

	public boolean destroy (Rental data) {
		// Ainda falta as relações
		sql = "DELETE FROM rentals WHERE id = ?";

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

	public Rental search (Rental data) {
		sql = "SELECT * FROM rentals WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";

		Connect connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Rental) rs;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}

	public Collection<Rental> list (String where) {
		sql = "SELECT * FROM rentals " + where;

		Connection connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Rental> rentals = new ArrayList<>();
			while (rs.next()) {
				rentals.add((Rental) rs);
			}

			return rentals;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}