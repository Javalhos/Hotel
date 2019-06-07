package Database;
import java.sql.*;

/**
 * DB
 */
public class DB {

	public static String host, database, user, password;
	public static int port;
	public static Connection connection = null;

	static {
		host = "localhost";
		database = "hotel";
		user = "root";
		password = "";
		port = 3306;
	}

	public static Connection openConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

		Class.forName("com.mysql.jdbc.Driver");
		return connection = DriverManager.getConnection(url, user, password);
	}

	public static void closeConnection() throws SQLException {
		connection.close();
	}
}
