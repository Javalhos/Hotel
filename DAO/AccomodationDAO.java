package DAO;

import Data.Accomodation;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class AccomodationDAO implements DAO<Accomodation> {
	private String sql = "";
	
	public boolean create (Accomodation data) {
		sql = "INSERT INTO acoomodations" + 
			"(rental_id, booking_id, user_cpf, total_value, status)" +
			"VALUES (?, ?, ?, ?, ?)";
			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getRentalId());
			pst.setInt(2, data.getBookingId());
			pst.setString(3, data.getUserCpf());
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Accomodation data) {
		sql = "UPDATE accomodations SET total_value = ?, status = ? WHERE id = ?";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

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
			e.printStackTrace();
			return false;
		}
	}

	public boolean destroy (Accomodation data) {
		sql = "DELETE FROM accomodations WHERE id = ?";
		
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

	public Accomodation search (Accomodation data) {
		sql = "SELECT * FROM accomodations WHERE id = " + data.getId() + " LIMIT 1";
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			Accomodation accomodation = new Accomodation();
			while(rs.next()) {
				accomodation.setId(rs.getInt("id"));
				accomodation.setRentalId(rs.getInt("rental_id"));
				accomodation.setBookingId(rs.getInt("booking_id"));
				accomodation.setUserCpf(rs.getString("user_cpf"));
				accomodation.setTotal(rs.getFloat("total_value"));
				accomodation.setStatus(rs.getString("status"));
			}

			return accomodation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Accomodation> list (String where) {
		sql = "SELECT * FROM accomodations " + where;
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Accomodation> accomodations = new ArrayList();
			while (rs.next()) {
				Accomodation accomodation = new Accomodation();

				accomodation.setId(rs.getInt("id"));
				accomodation.setRentalId(rs.getInt("rental_id"));
				accomodation.setBookingId(rs.getInt("booking_id"));
				accomodation.setUserCpf(rs.getString("user_cpf"));
				accomodation.setTotal(rs.getFloat("total_value"));
				accomodation.setStatus(rs.getString("status"));
				accomodations.add(accomodation);
			}

			return accomodations;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}