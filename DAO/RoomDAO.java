package DAO;

import Data.Room;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class RoomDAO implements DAO<Room> {
	private String sql = "";

	public boolean create (Room data) throws Exception {
		sql = "INSERT INTO rooms" + 
			"(room, beds, type, extension_phone, daily_rate, status)" +
			"VALUES (?, ?, ?, ?, ?, ?)";
			
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getRoom());
			pst.setInt(2, data.getBeds());
			pst.setString(3, data.getType());
			pst.setString(4, data.getExtensionPhone());
			pst.setFloat(5, data.getDailyRate());
			pst.setString(6, data.getStatus());
			
			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao criar quarto!", e);
		}
	}

	public boolean update (Room data) throws Exception {
		sql = "UPDATE rooms SET beds = ?, type = ?, extension_phone = ?"
				+ "daily_rate = ?, status = ? WHERE room = ?";
	
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(0, data.getRoom());
			pst.setInt(1, data.getBeds());
			pst.setString(2, data.getType());
			pst.setString(3, data.getExtensionPhone());
			pst.setFloat(4, data.getDailyRate());
			pst.setString(5, data.getStatus());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
 		} catch (Exception e) {
			throw new Exception("Erro ao atualizar quarto!", e);
		}
	}

	public boolean destroy (Room data) throws Exception {
		sql = "DELETE FROM rooms WHERE room = ?";

		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setInt(1, data.getRoom());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao deletar quarto!", e);
		}
	}

	public Room search (Room data) throws Exception {
		sql = "SELECT * FROM rooms WHERE room LIKE '%" + data.getRoom() + "%' LIMIT 1";
	
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Room) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar quarto!", e);
		}
	}

	public Collection<Room> list (String where) throws Exception {
		sql = "SELECT * FROM rooms " + where;

		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Room> rooms = new ArrayList<>();
			while (rs.next()) {
				rooms.add((Room) rs);
			}

			return rooms;
		} catch (Exception e) {
			throw new Exception("Erro ao listar quartos!", e);
		}
	}
}