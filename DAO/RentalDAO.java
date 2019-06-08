package DAO;

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

			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setString(1, data.getCpf());
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Rental data) {
		sql = "UPDATE rentals SET user_cpf = ?, room = ?, entry_date = ?, departure_data = ?, "
				+ "status = ?, value = ? WHERE id = ?";

				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setString(1, data.getCpf());
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean destroy (Rental data) {
		// Ainda falta as relações
		sql = "DELETE FROM rentals WHERE id = ?";

		
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

	public Rental search (Rental data) {
		sql = "SELECT * FROM rentals WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Rental rental = new Rental();

			while(rs.next()) {
				rental.setId(rs.getInt("id"));
				rental.setCpf(rs.getString("user_cpf"));
				rental.setRoom(rs.getInt("room"));
				rental.setEntryDate(rs.getString("entry_date"));
				rental.setDepartureDate(rs.getString("departure_date"));
				rental.setStatus(rs.getString("status"));
				rental.setValue(rs.getFloat("value"));
			}
			
			return rental;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Rental> list (String where) {
		sql = "SELECT * FROM rentals " + where;

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Rental> rentals = new ArrayList();
			while (rs.next()) {
				Rental rental = new Rental();

				rental.setId(rs.getInt("id"));
				rental.setCpf(rs.getString("user_cpf"));
				rental.setRoom(rs.getInt("room"));
				rental.setEntryDate(rs.getString("entry_date"));
				rental.setDepartureDate(rs.getString("departure_date"));
				rental.setStatus(rs.getString("status"));
				rental.setValue(rs.getFloat("value"));
				rentals.add(rental);
			}

			return rentals;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}