package src.Models;

// Java & 3rd Party
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

// Project
import src.Database.DB;
import src.Data.Accomodation;



public class AccomodationDAO implements DAO<Accomodation> {
	private String sql = "";

	public boolean create (Accomodation data) {
		sql = "INSERT INTO `accomodations` " +
		"(room, cpf, type, entry_date, departure_date, status, value) " +
		"VALUES (?, ?, ?, ?, ?, ?, ?)" ;

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getRoom());
			pst.setString(2, data.getCpf());
			pst.setString(3, data.getAccType());
			pst.setString(4, data.getEntryDate());
			pst.setString(5, data.getDepartureDate());
			pst.setString(6, data.getStatus());
			pst.setFloat(7, data.getAccValue());

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
		sql = "UPDATE accomodations SET room = ?, cpf = ?, type = ?, " +
		"entry_date = ?, departure_date = ?, status = ?, value = ? " +
		"WHERE id = '" + data.getId() + "'";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getRoom());
			pst.setString(2, data.getCpf());
			pst.setString(3, data.getAccType());
			pst.setString(4, data.getEntryDate());
			pst.setString(5, data.getDepartureDate());
			pst.setString(6, data.getStatus());
			pst.setFloat(7, data.getAccValue());

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
		sql = "DELETE FROM `accomodations` WHERE id = ?";

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
		sql = "SELECT * FROM accomodations WHERE id = '" + data.getId() + "'";

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Accomodation accomodation = new Accomodation();
			while(rs.next()) {
				accomodation.setId(rs.getInt("id"));
				accomodation.setRoom(rs.getInt("room"));
				accomodation.setCpf(rs.getString("cpf"));
				accomodation.setAccType(rs.getString("type"));
				accomodation.setEntryDate(rs.getString("entry_date"));
				accomodation.setDepartureDate(rs.getString("departure_date"));
				accomodation.setStatus(rs.getString("status"));
				accomodation.setAccValue(rs.getFloat("value"));
			}

			return accomodation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Accomodation> list (String where) {
		sql = "SELECT * FROM `accomodations` " + where;

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Accomodation> accomodations = new ArrayList<Accomodation>();
			while (rs.next()) {
				Accomodation accomodation = new Accomodation();

				accomodation.setId(rs.getInt("id"));
				accomodation.setRoom(rs.getInt("room"));
				accomodation.setCpf(rs.getString("cpf"));
				accomodation.setAccType(rs.getString("type"));
				accomodation.setEntryDate(rs.getString("entry_date"));
				accomodation.setDepartureDate(rs.getString("departure_date"));
				accomodation.setStatus(rs.getString("status"));
				accomodation.setAccValue(rs.getFloat("value"));
				accomodations.add(accomodation);
			}

			return accomodations;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
}