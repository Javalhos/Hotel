package Models;

import Data.Room;
import Database.DB;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class RoomDAO implements DAO<Room> {
	private String sql = "";

	public boolean create (Room data) {
		sql = "INSERT INTO rooms" + 
			"(room, beds, type, extension_phone, daily_rate, status)" +
			"VALUES (?, ?, ?, ?, ?, ?)";

		Connection connect = DB.openConnection();

		try {
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
			//TODO: handle exception
			return false;
		}
	}

	public boolean update (Room data) {
		sql = "UPDATE rooms SET beds = ?, type = ?, extension_phone = ?"
				+ "daily_rate = ?, status = ? WHERE room = ?";

		Connection connect = DB.openConnection();

		try {
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
			//TODO: handle exception
			return false;
		}
	}

	public boolean destroy (Room data) {
		sql = "DELETE FROM rooms WHERE room = ?";

		Connection connect = DB.openConnection();

		try {
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
			//TODO: handle exception
			return false;
		}
	}

	public Room search (Room data) {
		sql = "SELECT * FROM rooms WHERE room LIKE '%" + data.getRoom() + "%' LIMIT 1";

		Connect connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (Room) rs;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}

	public Collection<Room> list (String where) {
		sql = "SELECT * FROM rooms " + where;

		Connection connect = DB.openConnection();

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Room> rooms = new ArrayList<>();
			while (rs.next()) {
				rooms.add((Room) rs);
			}

			return rooms;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}