package com.alexander.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {
	private static String DB_USER = System.getenv("MYSQL_USER");
	private static String DB_PASSWORD = System.getenv("MYSQL_PASSWORD");;
	private static String DB_DATABASE = System.getenv("MYSQL_DATABASE");
	private static String DB_HOST = System.getenv("MYSQL_HOST");;
	private static String DB_PORT = System.getenv("MYSQL_PORT");

	// Cargando el driver de MySQL
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Obtiene la conexión a la base de datos MySQL
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", DB_HOST, DB_PORT, DB_DATABASE), DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Cierra la conexión a la base de datos
	public static void closeConnection(ResultSet rs, CallableStatement cs, Connection cn) {
		try {
			if (rs != null)
				rs.close();
			if (cs != null)
				cs.close();
			if (cn != null)
				cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
