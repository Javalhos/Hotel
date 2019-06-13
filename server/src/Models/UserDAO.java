package src.Models;

// Java & 3rd Party
import src.Data.User;
import src.Database.DB;

// Project
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class UserDAO implements DAO<User> {
	private String sql = "";

	public boolean create (User data) {
		sql = "INSERT INTO `users` (`cpf`, `name`, `email`, `password`, `contact_number`, `address`, `level`) VALUES (?, ?, ?, ?, ?, ?, ?)";
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setString(1, data.getCpf());
			pst.setString(2, data.getName());
			pst.setString(3, data.getEmail());
			pst.setString(4, data.getPassword());
			pst.setString(5, data.getContactNumber());
			pst.setString(6, data.getAddress());
			pst.setString(7, data.getLevel());

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

	public boolean update (User data) {
		sql = "UPDATE `users` SET `name` = ?, `email` = ?, `contact_number` = ?, `address` = ?, `level` = ? WHERE `cpf` = ?";
				
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setString(1, data.getName());
			pst.setString(2, data.getEmail());
			pst.setString(3, data.getContactNumber());
			pst.setString(4, data.getAddress());
			pst.setString(5, data.getLevel());
			pst.setString(6, data.getCpf());

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

	public boolean destroy (User data) {
		sql = "DELETE FROM users WHERE cpf = ?";
		
		try {
			DB.openConnection();
			PreparedStatement pst = DB.connection.prepareStatement(sql);

			pst.setString(1, data.getCpf());

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

	public User search (User data) {
		sql = "SELECT * FROM `users` WHERE `cpf` = '" + data.getCpf() +"'";

		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			User user = new User();
			if (!rs.next()) 
				return null;
			
			user.setCpf(rs.getString("cpf"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setContactNumber(rs.getString("contact_number"));
			user.setAddress(rs.getString("address"));
			user.setLevel(rs.getString("level"));

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<User> list (String where) {
		sql = "SELECT * FROM users " + where;
		
		try {
			DB.openConnection();
			Statement statement = DB.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();

				user.setCpf(rs.getString("cpf"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setContactNumber(rs.getString("contact_number"));
				user.setAddress(rs.getString("address"));
				user.setLevel(rs.getString("level"));
				users.add(user);
			}

			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}