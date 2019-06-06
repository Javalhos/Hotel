package Models;

import Data.Services;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ServicesDAO implements DAO<Services> {
	private String sql = "";

	public boolean create (Services data) {
		sql = "INSERT INTO services" + 
			"(name, description, value)" +
			"VALUES (?, ?, ?)";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

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
			//TODO: handle exception
			return false;
		}
	}

	public boolean update (Services data) {
		sql = "UPDATE services SET name = ?, description = ?, "
				+ "value = ?, status = ? WHERE id = ?";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

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
			//TODO: handle exception
			return false;
		}
	}

	public boolean destroy (Services data) {
		sql = "DELETE FROM services WHERE id = ?";

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

	public Services search (Services data) {
		sql = "SELECT * FROM services WHERE id LIKE '%" + data.getId() 
		+ "%' LIMIT 1";

		Connect connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Services) rs;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}

	public Collection<Services> list (String where) {
		sql = "SELECT * FROM services " + where;

		Connection connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Services> services = new ArrayList<>();
			while (rs.next()) {
				services.add((Services) rs);
			}

			return services;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}