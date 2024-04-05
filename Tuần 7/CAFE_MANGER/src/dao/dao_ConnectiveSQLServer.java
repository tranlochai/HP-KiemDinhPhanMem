package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class dao_ConnectiveSQLServer {
	public static Connection cn;
	public static void connective() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//System.out.println("ket noi sqlserver");
			String st = "jdbc:sqlserver://DESKTOP-7O8TV37:1433;databaseName=CAFE2;user=sa; password=12345";
			cn = DriverManager.getConnection(st);
			System.out.println("Connect Thanh Cong");
			//System.out.println("ket noi csdl");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
