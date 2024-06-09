package database;

import java.sql.DriverManager;

public class ConnectDB {
	public static void main(String[] args) {
		String str = "jdbc:sqlserver://LINH:1433;"
				+ "user=sa; password=Linh@2003; "
				+ "encrypt=true; trustServerCertificate=true;";;
					
	try(
		var con = DriverManager.getConnection(str);	
		){
			System.out.println("susccess");
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
