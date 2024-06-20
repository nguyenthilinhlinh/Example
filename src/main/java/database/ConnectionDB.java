package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static final String CONNECTION_STRING = "jdbc:sqlserver://LINH:1433;"
			+ "user=sa; password=Linh@2003; databaseName=STUDENT;"
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
	public static void CreateTableDB() {
		String query = """
				DROP TABLE IF EXISTS students
			            CREATE TABLE students
                  (
                  	msv INT PRIMARY KEY IDENTITY,
                  	name NVARCHAR(250),
                  	birthday DATE,
                  	address NVARCHAR(255),
					gender TINYINT 
                  )
				"""
				;
		 try (
			var con = getConnection();
			var stmt = con.createStatement()
			) 
		 {
	            stmt.executeUpdate(query);
	            System.out.println("Table created successfully");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public static void main(String[] args) {
		 CreateTableDB();
	    }
	}

