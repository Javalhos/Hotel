package src.Models;

import src.Data.Consumed;
import src.Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ConsumedDAO implements DAO<Consumed> {
	private String sql = "";

	public boolean create (Consumed data) {
		sql = "INSERT INTO consumed_services" + 
			"(accomodation_id, service_id, name, value)" +
			"VALUES (?, ?, ?, ?)";
			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getAccomodation());
			pst.setInt(2, data.getServiceId());
			pst.setString(3, data.getServiceName());
			pst.setFloat(4, data.getServiceValue());
			
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

	public boolean update (Consumed data) {
		sql = "UPDATE consumed_services SET accomodation_id = ?, service_id = ?, "
				+ "name  = ?, value = ? WHERE id = ?";
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setInt(1, data.getAccomodation());
			pst.setInt(2, data.getServiceId());
			pst.setString(3, data.getServiceName());
			pst.setFloat(4, data.getServiceValue());

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

	public boolean destroy (Consumed data) {
		sql = "DELETE FROM consumed_services WHERE id = ?";
		
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

	public Consumed search (Consumed data) {
		sql = "SELECT * FROM consumed_services WHERE id = " + data.getId() + " OR"
		+ "accomodation_id = " + data.getAccomodation() + " LIMIT 1";
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Consumed consumedService = new Consumed();

			while(rs.next()) {
				consumedService.setId(rs.getInt("id"));
				consumedService.setAccomodation(rs.getInt("accomodation_id"));
				consumedService.setServiceId(rs.getInt("service_id"));
				consumedService.setServiceName(rs.getString("name"));
				consumedService.setServiceValue(rs.getFloat("value"));
			}

			return consumedService;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Consumed> list (String where) {
		sql = "SELECT * FROM consumed_services " + where;
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Consumed> consumed = new ArrayList<Consumed>();
			while (rs.next()) {
				Consumed consumedService = new Consumed();

				consumedService.setId(rs.getInt("id"));
				consumedService.setAccomodation(rs.getInt("accomodation_id"));
				consumedService.setServiceId(rs.getInt("service_id"));
				consumedService.setServiceName(rs.getString("name"));
				consumedService.setServiceValue(rs.getFloat("value"));
				consumed.add(consumedService);
			}

			return consumed;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}