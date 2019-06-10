package src.Models;

import src.Data.Service;
import src.Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ServiceDAO implements DAO<Service> {
	private String sql = "";

	public boolean create (Service data) {
		sql = "INSERT INTO services (name, description, value) VALUES (?, ?, ?)";
			
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

	public boolean update (Service data) {
		sql = "UPDATE services SET name = ?, description = ?, value = ?, status = ? WHERE id = ?";
				
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

	public boolean destroy (Service data) {
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

	public Service search (Service data) {
		sql = "SELECT * FROM services WHERE id = ? LIMIT 1";

		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);
			pst.setInt(1, data.getId());
			ResultSet rs = pst.executeQuery();

			if (!rs.next())
			return null;

			Service service = new Service();
			service.setId(rs.getInt("id"));
			service.setName(rs.getString("name"));
			service.setDescription(rs.getString("description"));
			service.setValue(rs.getFloat("value"));

			return service;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Service> list (String where) {
		sql = "SELECT * FROM services " + where;

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Service> services = new ArrayList<Service>();
			while (rs.next()) {
				Service service = new Service();

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