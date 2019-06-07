package DAO;

// Arquivos
import Data.User;
import Database.DB;

// Imports necessários
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class UserDAO implements DAO<User> {
	private String sql = "";

	// Método de inserir
	public boolean create (User data) throws Exception {
		sql = "INSERT INTO users" + "(cpf, name, email, password, contact_number, address, birthday, level)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setString(1, data.getCpf());
			pst.setString(2, data.getName());
			pst.setString(3, data.getEmail());
			pst.setString(4, data.getPassword());
			pst.setString(5, data.getContactNumber());
			pst.setString(6, data.getAddress());
			pst.setString(7, data.getBirthday());
			pst.setString(8, data.getLevel());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao criar usuário!", e);
		}
	}

	public boolean update (User data) throws Exception {
		sql = "UPDATE users SET name = ?, email = ?, password = ?"
				+ "contact_number = ?, address = ?, birthday = ?, level = ? WHERE cpf = ?";
				
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setString(1, data.getName());
			pst.setString(2, data.getEmail());
			pst.setString(3, data.getPassword());
			pst.setString(4, data.getContactNumber());
			pst.setString(5, data.getAddress());
			pst.setString(6, data.getBirthday());
			pst.setString(7, data.getLevel());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar usuário!", e);
		}
	}

	public boolean destroy (User data) throws Exception {
		sql = "DELETE FROM users WHERE cpf = ?";
		
		try {
			Connection connect = DB.openConnection();
			PreparedStatement pst = connect.prepareStatement(sql);

			pst.setString(1, data.getCpf());

			if (pst.executeUpdate() > 0) {
				DB.closeConnection();
				return true;
			} else {
				DB.closeConnection();
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro ao deletar usuário!", e);
		}
	}

	public User search (User data) throws Exception {
		sql = "SELECT * FROM users WHERE cpf LIKE '%" + data + "%' OR" +
		"email LIKE '%" + data + "%' ORDER BY id_room";
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			return (User) rs;
		} catch (Exception e) {
			throw new Exception("Erro ao pesquisar usuário!", e);
		}
	}

	public Collection<User> list (String where) throws Exception {
		sql = "SELECT * FROM users " + where;
		
		try {
			Connection connect = DB.openConnection();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			Collection<User> users = new ArrayList<>();
			while (rs.next()) {
				users.add((User) rs);
			}

			return users;
		} catch (Exception e) {
			throw new Exception("Erro ao listar usuários!", e);
		}
	}
}