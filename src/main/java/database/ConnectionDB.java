package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static final String CONNECTION_STRING = "jdbc:sqlserver://LINH:1433;"
			+ "user=sa; password=Strong@Passw0rd; databaseName=studentdb;"
			+ "encrypt=true; trustServerCertificate=true;";;

	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING);
	}
	
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
