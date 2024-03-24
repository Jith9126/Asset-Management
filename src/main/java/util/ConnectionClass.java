package util;

import java.sql.*;
public class ConnectionClass {
	private Connection conn;
	private static ConnectionClass currConnection;
	
	private ConnectionClass(){
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/AssetDummy"
        );
		}catch (SQLException e) {
			System.out.println("Problem in sql Connection");
		}catch (ClassNotFoundException e) {
			System.out.println("Problem in Sql jar");
		}
		
	}
	
	
	public static ConnectionClass CreateCon(){
		if(currConnection == null) {
			currConnection = new ConnectionClass();
		}
		return currConnection;
		
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	
}
