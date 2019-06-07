package DAO;

import Data.Accomodation;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class AccomodationDAO implements DAO<Accomodation> {
	private String sql = "";
	
	public boolean create (Accomodation data) throws Exception {
		sql = "INSERT INTO acoomodations" + 
			"(rental_id, booking_id, user_cpf, consumed_services, total_value, status)" +
			"VALUES (?, ?, ?, ?, ?, ?)";
			
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getRentalId());
			pst.setInt(2, data.getBookingId());
			pst.setString(3, data.getUserCpf());
			pst.setInt(4, data.getConsumedServices());
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
			throw new Exception("Erro ao criar acomodação!", e);
		}
	}

	public boolean update (Accomodation data) throws Exception {
		sql = "UPDATE accomodations SET total_value = ?, status = ? WHERE id = ?";

		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

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
			throw new Exception("Erro ao atualizar acomodação!", e);
		}
	}

	public boolean destroy (Accomodation data) throws Exception {
		sql = "DELETE FROM accomodations WHERE id = ?";
		
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
			throw new Exception("Erro ao deletar acomodação!", e);
		}
	}

	public Accomodation search (Accomodation data) throws Exception {
		sql = "SELECT * FROM accomodations WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Accomodation) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar acomodação!", e);
		}
	}

	public Collection<Accomodation> list (String where) throws Exception {
		sql = "SELECT * FROM accomodations " + where;
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Accomodation> accomodations = new ArrayList<>();
			while (rs.next()) {
				accomodations.add((Accomodation) rs);
			}

			return accomodations;
		} catch (Exception e) {
			throw new Exception("Erro ao listar acomodações!", e);
		}
	}
}