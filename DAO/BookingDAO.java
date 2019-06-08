package DAO;

import Data.Booking;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class BookingDAO implements DAO<Booking> {
	private String sql = "";

	public boolean create (Booking data) {
		sql = "INSERT INTO bookings" + 
			"(user_cpf, room, entry_date, departure_date, booking_tax, status, value)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

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
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Booking data) {
		sql = "UPDATE bookings SET user_cpf = ?, room = ?, entry_date = ?, departure_date = ?, "
				+ "booking_tax = ?, status = ?, value = ? WHERE id = ?";
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

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
			e.printStackTrace();
			return false;
		}
	}

	public boolean destroy (Booking data) {
		sql = "DELETE FROM bookings WHERE id = ?";
		
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

	public Booking search (Booking data) {
		sql = "SELECT * FROM bookings WHERE id LIKE '%" + data.getId() + "%' LIMIT 1";
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Booking booking = new Booking();

			while(rs.next()) {
				booking.setId(rs.getInt("id"));
				booking.setCpf(rs.getString("user_cpf"));
				booking.setRoom(rs.getInt("room"));
				booking.setEntryDate(rs.getString("entry_date"));
				booking.setDepartureDate(rs.getString("departure_date"));
				booking.setBookTax(rs.getFloat("booking_tax"));
				booking.setStatus(rs.getString("status"));
				booking.setValue(rs.getFloat("value"));
			}

			return booking;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Booking> list (String where) {
		sql = "SELECT * FROM bookings " + where;
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<Booking> bookings = new ArrayList();
			while (rs.next()) {
				Booking booking = new Booking();

				booking.setId(rs.getInt("id"));
				booking.setCpf(rs.getString("user_cpf"));
				booking.setRoom(rs.getInt("room"));
				booking.setEntryDate(rs.getString("entry_date"));
				booking.setDepartureDate(rs.getString("departure_date"));
				booking.setBookTax(rs.getFloat("booking_tax"));
				booking.setStatus(rs.getString("status"));
				booking.setValue(rs.getFloat("value"));
				bookings.add(booking);
			}

			return bookings;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}