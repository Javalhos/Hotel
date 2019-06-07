package DAO;

import Data.ConsumedServices;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ConsumedServicesDAO implements DAO<ConsumedServices> {
	private String sql = "";

	public boolean create (ConsumedServices data) throws Exception {
		sql = "INSERT INTO consumed_services" + 
			"(accomodation_id, service_id, name, value)" +
			"VALUES (?, ?, ?, ?)";
			
		try {
			Connection connect = DB.openConnection();
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
			throw new Exception("Erro ao criar serviço consumido!", e);
		}
	}

	public boolean update (ConsumedServices data) throws Exception {
		sql = "UPDATE consumed_services SET accomodation_id = ?, service_id = ?, "
				+ "name  = ?, value = ? WHERE id = ?";
				
		try {
			Connection connect = DB.openConnection();
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
			throw new Exception("Erro ao atualizar serviço consumido!", e);
		}
	}

	public boolean destroy (ConsumedServices data) throws Exception {
		sql = "DELETE FROM consumed_services WHERE id = ?";
		
		try {
			Connection connect = DB.openConnection();
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
			throw new Exception("Erro ao deletar serviço consumido!", e);
		}
	}

	public ConsumedServices search (ConsumedServices data) throws Exception {
		sql = "SELECT * FROM consumed_services WHERE id LIKE '%" + data.getId() + "%' OR"
		+ "accomodation_id LIKE '%" + data.getAccomodation() + "%' LIMIT 1";
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (ConsumedServices) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar serviço consumido!", e);
		}
	}

	public Collection<ConsumedServices> list (String where) throws Exception {
		sql = "SELECT * FROM consumed_services " + where;
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<ConsumedServices> consumedServices = new ArrayList<>();
			while (rs.next()) {
				consumedServices.add((ConsumedServices) rs);
			}

			return consumedServices;
		} catch (Exception e) {
			throw new Exception("Erro ao listar serviços consumidos!", e);
		}
	}
}