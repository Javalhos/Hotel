package DAO;

import Data.Booking;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class BookingDAO implements DAO<Booking> {
	private String sql = "";

	public boolean create (Booking data) throws Exception {
		sql = "INSERT INTO bookings" + 
			"(user_cpf, room, entry_date, departure_date, booking_tax, status, value)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setString(1, data.getCpf());
			pst.setInt(2, data.getRoom());
			pst.setString(3, data.getEntryDate());
			pst.setString(4, data.getDepartureDate());
			pst.setFloat(5, data.getBookTax());
			pst.setString(6, data.getStatus());
			pst.setFloat(7, data.getValue());
			
			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao criar reserva!", e);
		}
	}

	public boolean update (Booking data) throws Exception {
		sql = "UPDATE bookings SET user_cpf = ?, room = ?, entry_date = ?, departure_date = ?, "
				+ "booking_tax = ?, status = ?, value = ? WHERE id = ?";
				
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(0, data.getId());
			pst.setString(1, data.getCpf());
			pst.setInt(2, data.getRoom());
			pst.setString(3, data.getEntryDate());
			pst.setString(4, data.getDepartureDate());
			pst.setFloat(5, data.getBookTax());
			pst.setString(6, data.getStatus());
			pst.setFloat(7, data.getValue());
		
			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
 		} catch (Exception e) {
			throw new Exception("Erro ao atualizar reserva!", e);
		}
	}

	public boolean destroy (Booking data) throws Exception {
		sql = "DELETE FROM bookings WHERE id = ?";
		
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
			throw new Exception("Erro ao deletar reserva!", e);
		}
	}

	public Booking search (Booking data) throws Exception {
		sql = "SELECT * FROM bookings WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Booking) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar reserva!", e);
		}
	}

	public Collection<Booking> list (String where) throws Exception {
		sql = "SELECT * FROM bookings " + where;
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Booking> bookings = new ArrayList<>();
			while (rs.next()) {
				bookings.add((Booking) rs);
			}

			return bookings;
		} catch (Exception e) {
			throw new Exception("Erro ao listar reservas!", e);
		}
	}
}