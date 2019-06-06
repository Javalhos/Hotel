package Models;

import Data.ConsumedServices;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ConsumedServicesDAO implements DAO<ConsumedServices> {
	private String sql = "";

	public boolean create (ConsumedServices data) {
		sql = "INSERT INTO consumed_services" + 
			"(accomodation_id, service_id, name, value)" +
			"VALUES (?, ?, ?, ?)";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

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
			//TODO: handle exception
			return false;
		}
	}

	public boolean update (ConsumedServices data) {
		sql = "UPDATE consumed_services SET accomodation_id = ?, service_id = ?, "
				+ "name  = ?, value = ? WHERE id = ?";

		Connection connect = DB.openConnection();

		try {
			PreparedStatement pst = connect.prepareStatement(sql);

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
			//TODO: handle exception
			return false;
		}
	}

	public boolean destroy (ConsumedServices data) {
		sql = "DELETE FROM consumed_services WHERE id = ?";

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

	public ConsumedServices search (ConsumedServices data) {
		sql = "SELECT * FROM consumed_services WHERE id LIKE '%" + data.getId() + "%' OR"
		+ "accomodation_id LIKE '%" + data.getAccomodation() + "%' LIMIT 1";

		Connect connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (ConsumedServices) rs;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}

	public Collection<ConsumedServices> list (String where) {
		sql = "SELECT * FROM consumed_services " + where;

		Connection connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<ConsumedServices> consumedServices = new ArrayList<>();
			while (rs.next()) {
				consumedServices.add((ConsumedServices) rs);
			}

			return consumedServices;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}