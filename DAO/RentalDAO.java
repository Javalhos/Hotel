package DAO;

import Data.Rental;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class RentalDAO implements DAO<Rental> {
	private String sql = "";

	public boolean create (Rental data) throws Exception {
		sql = "INSERT INTO rentals" + 
			"(user_cpf, room, entry_date, departure_date, status, value)" +
			"VALUES (?, ?, ?, ?, ?, ?)";

			
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

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
			throw new Exception("Erro ao criar aluguel!", e);
		}
	}

	public boolean update (Rental data) throws Exception {
		sql = "UPDATE rentals SET user_cpf = ?, room = ?, entry_date = ?, departure_data = ?, "
				+ "status = ?, value = ? WHERE id = ?";

				
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

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
			throw new Exception("Erro ao atualizar aluguel!", e);
		}
	}

	public boolean destroy (Rental data) throws Exception {
		// Ainda falta as relações
		sql = "DELETE FROM rentals WHERE id = ?";

		
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
			throw new Exception("Erro ao deletar aluguel!", e);
		}
	}

	public Rental search (Rental data) throws Exception {
		sql = "SELECT * FROM rentals WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";

		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Rental) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar aluguel!", e);
		}
	}

	public Collection<Rental> list (String where) throws Exception {
		sql = "SELECT * FROM rentals " + where;

		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Rental> rentals = new ArrayList<>();
			while (rs.next()) {
				rentals.add((Rental) rs);
			}

			return rentals;
		} catch (Exception e) {
			throw new Exception("Erro ao listar alugueis!", e);
		}
	}
}