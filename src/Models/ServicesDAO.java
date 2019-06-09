package Models;

import src.Data.Services;
import src.Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ServicesDAO implements DAO<Services> {
	private String sql = "";

	public boolean create (Services data) {
		sql = "INSERT INTO services" + 
			"(name, description, value)" +
			"VALUES (?, ?, ?)";
			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setString(1, data.getName());
			pst.setString(2, data.getDescription());
			pst.setFloat(3, data.getValue());
			
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

	public boolean update (Services data) {
		sql = "UPDATE services SET name = ?, description = ?, "
				+ "value = ?, status = ? WHERE id = ?";
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setString(1, data.getName());
			pst.setString(2, data.getDescription());
			pst.setFloat(3, data.getValue());

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

	public boolean destroy (Services data) {
		sql = "DELETE FROM services WHERE id = ?";
		
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

	public Services search (Services data) {
		sql = "SELECT * FROM services WHERE id = '" + data.getId() 
		+ "' LIMIT 1";

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Services service = new Services();

			while(rs.next()) {
				service.setId(rs.getInt("id"));
				service.setName(rs.getString("name"));
				service.setDescription(rs.getString("description"));
				service.setValue(rs.getFloat("value"));
			}

			return service;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Services> list (String where) {
		sql = "SELECT * FROM services " + where;

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Services> services = new ArrayList();
			while (rs.next()) {
				Services service = new Services();

				service.setId(rs.getInt("id"));
				service.setName(rs.getString("name"));
				service.setDescription(rs.getString("description"));
				service.setValue(rs.getFloat("value"));
				services.add(service);
			}

			return services;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}