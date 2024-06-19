package database;

import java.sql.DriverManager;

public class CreateTableDB {
	public static void main(String[] args) {
		String str = "jdbc:sqlserver://MYWORLD:1433;"
			+ "databaseName=c2304l111nk; user=sa; password=1234567; "
			+ "encrypt=true; trustServerCertificate=true;";
		
		try (
			var con = DriverManager.getConnection(str);
			var stmt =con.createStatement();
		){
			String query ="""
			            DROP TABLE IF EXISTS students
			            CREATE TABLE students
                  (
                  	msv INT PRIMARY KEY IDENTITY,
                  	name NVARCHAR(250),
                  	birthday DATE,
                  	address NVARCHAR(255),
					gender TINYINT 
                  )
			              """;
			stmt.executeUpdate(query);
			System.out.println("success table");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
