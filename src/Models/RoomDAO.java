package src.Models;

// Java & 3rd Party
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

// Project
import src.Data.Room;
import src.Database.DB;

public class RoomDAO implements DAO<Room> {
	private String sql = "";

	public boolean create (Room data) {
		sql = "INSERT INTO rooms" + 
			"(room, beds, type, extension_phone, daily_rate, status)" +
			"VALUES (?, ?, ?, ?, ?, ?)";

			
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

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
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Room data) {
		sql = "UPDATE rooms SET beds = ?, type = ?, extension_phone = ?, "
				+ "daily_rate = ?, status = ? WHERE room = '" + data.getRoom() +"'";
		System.out.println(sql);
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

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
			e.printStackTrace();
			return false;
		}
	}

	public boolean destroy (Room data) {
		sql = "DELETE FROM rooms WHERE room = ?";

		try {
			DB.openConnection();

			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setInt(1, data.getRoom());

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

	public Room search (Room data) {
		sql = "SELECT * FROM rooms WHERE room LIKE '%" + data.getRoom() + "%' LIMIT 1";

		try {
			DB.openConnection();

			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Room room = new Room();
			while(rs.next()) {
				room.setRoom(rs.getInt("room"));
				room.setBeds(rs.getInt("beds"));
				room.setDailyRate(rs.getFloat("daily_rate"));
				room.setExtensionPhone(rs.getString("extension_phone"));
				room.setStatus(rs.getString("status"));
				room.setType(rs.getString("type"));
			}

			return room;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Room> list (String where) {
		sql = "SELECT * FROM rooms " + where;

		try {
			DB.openConnection();

			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			// Não sei se tá certo
			Collection<Room> rooms = new ArrayList<Room>();
			while (rs.next()) {
				Room room = new Room();

				room.setRoom(rs.getInt("room"));
				room.setBeds(rs.getInt("beds"));
				room.setDailyRate(rs.getFloat("daily_rate"));
				room.setExtensionPhone(rs.getString("extension_phone"));
				room.setStatus(rs.getString("status"));
				room.setType(rs.getString("type"));
				rooms.add(room);
			}

			return rooms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}