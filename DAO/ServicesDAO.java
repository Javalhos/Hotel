package DAO;

import Data.Services;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class ServicesDAO implements DAO<Services> {
	private String sql = "";

	public boolean create (Services data) throws Exception {
		sql = "INSERT INTO services" + 
			"(name, description, value)" +
			"VALUES (?, ?, ?)";
			
		try {
			Connection connect = DB.openConnection();
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
			throw new Exception("Erro ao criar serviço!", e);
		}
	}

	public boolean update (Services data) throws Exception {
		sql = "UPDATE services SET name = ?, description = ?, "
				+ "value = ?, status = ? WHERE id = ?";
				
		try {
			Connection connect = DB.openConnection();
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
			throw new Exception("Erro ao atualizar serviço!", e);
		}
	}

	public boolean destroy (Services data) throws Exception {
		sql = "DELETE FROM services WHERE id = ?";
		
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
			throw new Exception("Erro ao deletar serviço!", e);
		}
	}

	public Services search (Services data) throws Exception {
		sql = "SELECT * FROM services WHERE id LIKE '%" + data.getId() 
		+ "%' LIMIT 1";

		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Services) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar serviço!", e);
		}
	}

	public Collection<Services> list (String where) throws Exception {
		sql = "SELECT * FROM services " + where;

		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Services> services = new ArrayList<>();
			while (rs.next()) {
				services.add((Services) rs);
			}

			return services;
		} catch (Exception e) {
			throw new Exception("Erro ao listar serviços!", e);
		}
	}
}